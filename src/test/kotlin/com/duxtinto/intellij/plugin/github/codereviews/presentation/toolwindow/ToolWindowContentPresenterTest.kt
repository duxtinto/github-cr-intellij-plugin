package com.duxtinto.intellij.plugin.github.codereviews.presentation.toolwindow

import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.GetAllMyOpenForRepoInteractor
import com.duxtinto.intellij.plugin.github.codereviews.domain.repositories.FindGithubRepoForRootFolderInteractor
import com.duxtinto.intellij.plugin.github.codereviews.helpers.fixtures.Fixture
import com.duxtinto.intellij.plugin.github.codereviews.ide.acl.presentation.toolwindow.ToolWindowContentPresenter
import com.duxtinto.intellij.plugin.github.codereviews.presentation.reviewee.RevieweeContent
import com.duxtinto.intellij.plugin.github.codereviews.presentation.reviewer.ReviewerContent
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
    private lateinit var pullRequestFetcher: GetAllMyOpenForRepoInteractor

    @Injectable
    private lateinit var contentFactory: ContentFactory

    @Injectable
    private lateinit var contentManager: ContentManager

    @Injectable
    private lateinit var revieweeView: RevieweeContent.View

    @Injectable
    private lateinit var  revieweePresenter: RevieweeContent.Presenter

    @Injectable
    private lateinit var  reviewerView: ReviewerContent.View

    @Injectable
    private lateinit var  reviewerPresenter: ReviewerContent.Presenter

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

                githubRepoFinder.run(Unit)
                result = null
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
                githubRepoFinder.run(Unit)
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