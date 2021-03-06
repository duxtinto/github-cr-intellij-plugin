package com.duxtinto.intellij.plugin.github.codereviews.net.issues

import com.duxtinto.intellij.plugin.github.codereviews.domain.repositories.FindGithubRepoForRootFolderInteractor
import com.duxtinto.intellij.plugin.github.codereviews.helpers.fixtures.Fixture
import com.duxtinto.intellij.plugin.github.codereviews.ide.acl.services.github.issues.IdeaIssueLoader
import com.duxtinto.intellij.plugin.github.codereviews.net.issues.apiv3.IssueFetcher
import mockit.Expectations
import mockit.Injectable
import mockit.Tested
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class ApiV3IssueFetcherTest {
    @Injectable
    lateinit var apiLoader : IdeaIssueLoader

    @Injectable
    lateinit var rootRepositoryFinder: FindGithubRepoForRootFolderInteractor

    @Tested
    lateinit var fetcher : IssueFetcher

    @Test
    @DisplayName("fetch issue by id, if the issue exist in the remote root repository")
    fun fetchIssueByIdHappyPath() {
        // Arrange
        val remoteRepository = Fixture.repository().build()
        val expectedIssue = Fixture.issue().build()

        object : Expectations() {
            init {
                rootRepositoryFinder.run(Unit)
                result = remoteRepository

                apiLoader.loadIssueById(remoteRepository, expectedIssue.number)
                result = expectedIssue
            }
        }

        // Act
        val issue = fetcher.fetchIssueById(expectedIssue.number)

        // Assert
        assertThat(issue).isSameAs(expectedIssue)
    }

    @Test
    @DisplayName("fetch issue by id, if remote root remository doesn't exist")
    fun fetchIssueByIdIfNoRootRepo() {
        // Arrange
        object : Expectations() {
            init {
                rootRepositoryFinder.run(Unit)
                result = null
            }
        }

        // Act
        val issue = fetcher.fetchIssueById(182)

        // Assert
        assertThat(issue).isNull()
    }

    @Test
    @DisplayName("fetch issue by id, if can't find issue in remote root remository")
    fun fetchIssueByIdIfIssueNotFound() {
        // Arrange
        val remoteRepository = Fixture.repository().build()

        object : Expectations() {
            init {
                rootRepositoryFinder.run(Unit)
                result = remoteRepository

                apiLoader.loadIssueById(remoteRepository, 182)
                result = null
            }
        }

        // Act
        val issue = fetcher.fetchIssueById(182)

        // Assert
        assertThat(issue).isNull()
    }
}