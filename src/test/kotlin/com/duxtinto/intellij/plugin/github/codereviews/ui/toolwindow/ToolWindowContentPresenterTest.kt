package com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow

import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.GetAllOpenForRepoInteractor
import com.duxtinto.intellij.plugin.github.codereviews.domain.repositories.FindGithubRepoForRootFolderInteractor
import com.duxtinto.intellij.plugin.github.codereviews.helpers.fixtures.Fixture
import com.intellij.ui.content.Content
import com.intellij.ui.content.ContentFactory
import com.intellij.ui.content.ContentManager
import mockit.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class ToolWindowContentPresenterTest {
    @Injectable
    private lateinit var githubRepoFinder: FindGithubRepoForRootFolderInteractor

    @Injectable
    private lateinit var pullRequestFetcher: GetAllOpenForRepoInteractor

    @Injectable
    private lateinit var contentFactory: ContentFactory

    @Injectable
    private lateinit var contentManager: ContentManager

    @Injectable
    private lateinit var revieweeView: ToolWindowContent.Reviewee.View

    @Injectable
    private lateinit var  revieweePresenter: ToolWindowContent.Reviewee.Presenter

    @Injectable
    private lateinit var  reviewerView: ToolWindowContent.Reviewer.View

    @Injectable
    private lateinit var  reviewerPresenter: ToolWindowContent.Reviewer.Presenter

    @Tested
    private lateinit var presenter: ToolWindowContentPresenter

    @Test
    @DisplayName("on initialization, presenter should set both reviewee & reviewer views")
    internal fun init() {
        // Assert
        object : FullVerifications() {
            init {
                revieweePresenter.setView(revieweeView)
                reviewerPresenter.setView(reviewerView)
            }
        }
    }

    @Test
    @DisplayName("present tool window should present both, reviewee & reviewer content")
    internal fun presentToolWindow(
            @Mocked revieweeContent: Content,
            @Mocked reviewerContent: Content) {
        // Arrange
        object : Expectations() {
            init {
                contentFactory.createContent(revieweeView.content, "Reviewee", anyBoolean)
                result = revieweeContent

                contentFactory.createContent(reviewerView.content, "Reviewer", anyBoolean)
                result = reviewerContent
            }
        }

        // Act
        presenter.displayToolWindow()

        // Assert
        object : FullVerifications() {
            init {
                contentManager.addContent(revieweeContent)
                contentManager.addContent(reviewerContent)
            }
        }
    }

    @Test
    @DisplayName("present tool window, If the root repository is ready, should present pull requests")
    internal fun presentToolWindowIfRepositoryIsReady(
            @Mocked revieweeContent: Content,
            @Mocked reviewerContent: Content) {
        // Arrange
        val repository = Fixture.repository().build()
        val pullRequests = Fixture.pullRequest().buildList(4)

        object : Expectations() {
            init {
                githubRepoFinder.run(null)
                result = repository

                pullRequestFetcher.run(repository)
                result = pullRequests
            }
        }

        // Act
        presenter.displayToolWindow()

        // Assert
        object : Verifications() {
            init {
                revieweePresenter.presentPullRequests(pullRequests)
            }
        }
    }
}