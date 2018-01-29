package com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow

import com.duxtinto.intellij.plugin.github.codereviews.di.contracts.DiContainerAware
import com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow.codereviews.CodeReviews
import com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow.pullrequestlist.PullRequestList
import com.intellij.openapi.project.DumbAware
import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.impl.ToolWindowImpl

import javax.inject.Inject
import javax.inject.Named

class ToolWindowFactory : com.intellij.openapi.wm.ToolWindowFactory, DiContainerAware, DumbAware {
    @Inject
    @field:Named("GH_Reviews")
    internal lateinit var myToolWindow: ToolWindowImpl

    @Inject
    internal lateinit var revieweeView: ToolWindowContent.RevieweeView

    @Inject
    internal lateinit var reviewerView: ToolWindowContent.ReviewerView

    @Inject
    internal lateinit var presenter: ToolWindowContent.Presenter

    @Inject
    internal lateinit var pullRequestListView: PullRequestList.View

    @Inject
    internal lateinit var pullRequestListPresenter: PullRequestList.Presenter

    @Inject
    internal lateinit var codeReviewsView: CodeReviews.View

    @Inject
    internal lateinit var codeReviewsPresenter: CodeReviews.Presenter

    override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {
        getProjectContainer(project).inject(this)

        if (myToolWindow.id != (toolWindow as ToolWindowImpl).id) {
            return
        }

        presenter.setRevieweeView(revieweeView)
        presenter.setReviewerView(reviewerView)

        pullRequestListPresenter.setView(pullRequestListView)
        codeReviewsPresenter.setView(codeReviewsView)
    }
}
