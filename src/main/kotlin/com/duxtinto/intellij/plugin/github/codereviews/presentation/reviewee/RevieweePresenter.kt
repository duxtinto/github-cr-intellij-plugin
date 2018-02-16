package com.duxtinto.intellij.plugin.github.codereviews.presentation.reviewee

import com.duxtinto.intellij.plugin.github.codereviews.di.qualifiers.Reviewee
import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.PullRequestEntity
import com.duxtinto.intellij.plugin.github.codereviews.presentation.codereviews.CodeReviews
import com.duxtinto.intellij.plugin.github.codereviews.presentation.pullrequestlist.PullRequestList
import javax.inject.Inject

class RevieweePresenter
    @Inject
    constructor(
            @Reviewee pullRequestListView: PullRequestList.View,
            @Reviewee private val pullRequestListPresenter: PullRequestList.Presenter,
            codeReviewsView: CodeReviews.View,
            private val codeReviewsPresenter: CodeReviews.Presenter)
    : RevieweeContent.Presenter {

    private lateinit var view: RevieweeContent.View

    init {
        pullRequestListPresenter.setView(pullRequestListView)
        codeReviewsPresenter.setView(codeReviewsView)
    }

    override fun setView(view: RevieweeContent.View) {
        this.view = view
    }

    override fun presentPullRequests(pullRequests: List<PullRequestEntity>) {
        codeReviewsPresenter.clear()
        pullRequestListPresenter.displayPullRequests(pullRequests)
    }
}