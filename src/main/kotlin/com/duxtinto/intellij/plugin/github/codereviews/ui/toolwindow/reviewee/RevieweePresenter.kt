package com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow.reviewee

import com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow.ToolWindowContent
import com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow.codereviews.CodeReviews
import com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow.pullrequestlist.PullRequestList
import javax.inject.Inject

class RevieweePresenter
    @Inject
    constructor(
            pullRequestListView: PullRequestList.View,
            pullRequestListPresenter: PullRequestList.Presenter,
            codeReviewsView: CodeReviews.View,
            codeReviewsPresenter: CodeReviews.Presenter)
    : ToolWindowContent.Reviewee.Presenter {

    private lateinit var view: ToolWindowContent.Reviewee.View

    init {
        pullRequestListPresenter.setView(pullRequestListView)
        codeReviewsPresenter.setView(codeReviewsView)
    }

    override fun setView(view: ToolWindowContent.Reviewee.View) {
        this.view = view
    }
}