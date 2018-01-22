package com.duxtinto.intellij.plugin.github.codereviews.ide.acl.services

import com.duxtinto.intellij.plugin.github.codereviews.helpers.fixtures.Fixture
import com.duxtinto.intellij.plugin.github.codereviews.ide.acl.entities.GithubConnectionExt
import com.duxtinto.intellij.plugin.github.codereviews.ide.acl.services.issues.IssueDataMapper
import mockit.*
import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.plugins.github.api.GithubApiUtil
import org.jetbrains.plugins.github.api.GithubConnection
import org.jetbrains.plugins.github.api.data.GithubIssue
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.io.IOException

internal class GithubApiV3IssueLoaderTest {

    @Injectable
    lateinit var connection: GithubConnectionExt

    @Injectable
    lateinit var ideDataMapper: IssueDataMapper

    @Tested
    lateinit var loader: GithubApiV3IssueLoader

    @Test
    @DisplayName("load issue by Id, should search issue on remote repository")
    fun loadIssueByIdHappyPath(
            @Mocked ideIssue: GithubIssue,
            @Mocked ideConnection: GithubConnection) {
        // Arrange
        val expectedIssue = Fixture.issue().build()
        val repository = Fixture.repository().build()

        object : Expectations() {
            init {
                connection.delegate()
                result = ideConnection

                ideDataMapper.toEntity(ideIssue)
                result = expectedIssue
            }
        }

        object : Expectations(GithubApiUtil::class.java) {
            init {
                GithubApiUtil.getIssue(ideConnection, repository.ownerName, repository.name, expectedIssue.number.toString())
                result = ideIssue
            }
        }

        // Act
        val issue = loader.loadIssueById(repository, expectedIssue.number)

        // Assert
        assertThat(issue).isEqualTo(expectedIssue)
    }

    @Test
    @DisplayName("load issue by Id, if issue can't be loaded from github, should return null")
    fun loadIssueByIdIfLoadError(@Mocked ideConnection: GithubConnection) {
        // Arrange
        val expectedIssue = Fixture.issue().build()
        val repository = Fixture.repository().build()

        object : Expectations() {
            init {
                connection.delegate()
                result = ideConnection
            }
        }

        object : Expectations(GithubApiUtil::class.java) {
            init {
                GithubApiUtil.getIssue(ideConnection, anyString, anyString, anyString)
                result = IOException()
            }
        }

        // Act
        assertThrows(IOException::class.java) {
            loader.loadIssueById(repository, expectedIssue.number)
        }
    }
}