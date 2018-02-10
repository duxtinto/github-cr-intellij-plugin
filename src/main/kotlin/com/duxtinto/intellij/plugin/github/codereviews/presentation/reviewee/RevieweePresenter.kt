package com.duxtinto.intellij.plugin.github.codereviews.presentation.reviewee

import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.PullRequestEntity
import com.duxtinto.intellij.plugin.github.codereviews.ide.acl.presentation.toolwindow.ToolWindowContent
import com.duxtinto.intellij.plugin.github.codereviews.presentation.codereviews.CodeReviews
import com.duxtinto.intellij.plugin.github.codereviews.presentation.pullrequestlist.PullRequestList
import javax.inject.Inject

class RevieweePresenter
    @Inject
    constructor(
            pullRequestListView: PullRequestList.View,
            private val pullRequestListPresenter: PullRequestList.Presenter,
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

    override fun presentPullRequests(pullRequests: List<PullRequestEntity>) {
        pullRequestListPresenter.displayPullRequests(pullRequests)
    }
}