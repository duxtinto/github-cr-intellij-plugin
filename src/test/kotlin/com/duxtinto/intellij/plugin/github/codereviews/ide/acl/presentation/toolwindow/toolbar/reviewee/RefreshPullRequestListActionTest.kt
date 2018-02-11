package com.duxtinto.intellij.plugin.github.codereviews.ide.acl.presentation.toolwindow.toolbar.reviewee

import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.GetAllMyOpenForRepoInteractor
import com.duxtinto.intellij.plugin.github.codereviews.domain.repositories.FindGithubRepoForRootFolderInteractor
import com.duxtinto.intellij.plugin.github.codereviews.helpers.fixtures.Fixture
import com.duxtinto.intellij.plugin.github.codereviews.presentation.reviewee.RevieweeContent
import com.intellij.openapi.actionSystem.AnActionEvent
import mockit.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class RefreshPullRequestListActionTest {
    @Injectable
    private lateinit var githubRepoFinder: FindGithubRepoForRootFolderInteractor
    @Injectable
    private lateinit var pullRequestFetcher: GetAllMyOpenForRepoInteractor
    @Injectable
    private lateinit var revieweePresenter: RevieweeContent.Presenter

    @Tested
    private lateinit var sut: RefreshPullRequestListAction

    @Test
    @DisplayName("on action performed, the pull request list has to be updated")
    fun actionPerformedHappyPath(@Mocked event: AnActionEvent) {
        // Arrange
        val repository = Fixture.repository().build()
        val pullRequests = Fixture.pullRequest().buildList(3)

        object : Expectations() {
            init {
                githubRepoFinder.run(Unit)
                result = repository

                pullRequestFetcher.run(repository)
                result = pullRequests
            }
        }

        // Act
        sut.actionPerformed(event)

        // Assert
        object : FullVerifications() {
            init {
                revieweePresenter.presentPullRequests(pullRequests)
            }
        }
    }

    @Test
    @DisplayName("on action performed, if there is no root repository, should do nothing")
    fun actionPerformedIfNoRepository(@Mocked event: AnActionEvent) {
        // Arrange
        object : Expectations() {
            init {
                githubRepoFinder.run(Unit)
                result = null
            }
        }

        // Act
        sut.actionPerformed(event)

        // Assert
        object : FullVerifications() {
            init {
            }
        }
    }

    @Test
    @DisplayName("on action performed, if there is an error while fetching the pull requests, should do nothing")
    fun actionPerformed(@Mocked event: AnActionEvent) {
        // Arrange
        val repository = Fixture.repository().build()

        object : Expectations() {
            init {
                githubRepoFinder.run(Unit)
                result = repository

                pullRequestFetcher.run(repository)
                result = null
            }
        }

        // Act
        sut.actionPerformed(event)

        // Assert
        object : FullVerifications() {
            init {
            }
        }
    }
}