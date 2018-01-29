package com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow

import com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow.codereviews.CodeReviews
import com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow.pullrequestlist.PullRequestList
import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.impl.ToolWindowImpl
import mockit.Injectable
import mockit.Tested
import mockit.Verifications
import org.junit.jupiter.api.Test

internal class ToolWindowFactoryTest {
    @Injectable
    private lateinit var GH_Reviews: ToolWindowImpl

    @Injectable
    private lateinit var revieweeView: ToolWindowContent.RevieweeView

    @Injectable
    private lateinit var reviewerView: ToolWindowContent.ReviewerView

    @Injectable
    private lateinit var presenter: ToolWindowContent.Presenter

    @Injectable
    private lateinit var pullRequestListView: PullRequestList.View

    @Injectable
    private lateinit var pullRequestListPresenter: PullRequestList.Presenter

    @Injectable
    private lateinit var codeReviewsView: CodeReviews.View

    @Injectable
    private lateinit var codeReviewsPresenter: CodeReviews.Presenter

    @Tested
    private lateinit var factory: ToolWindowFactory

    @Test
    fun createToolWindowContent(@Injectable project: Project) {
        // Act
        factory.createToolWindowContent(project, GH_Reviews)

        // Assert
        object : Verifications() {
            init {
                presenter.setRevieweeView(revieweeView)
                presenter.setReviewerView(reviewerView)
                pullRequestListPresenter.setView(pullRequestListView)
                codeReviewsPresenter.setView(codeReviewsView)
            }
        }
    }
}