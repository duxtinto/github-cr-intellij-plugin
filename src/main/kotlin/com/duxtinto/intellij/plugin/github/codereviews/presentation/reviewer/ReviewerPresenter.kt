package com.duxtinto.intellij.plugin.github.codereviews.presentation.reviewer

import com.duxtinto.intellij.plugin.github.codereviews.di.qualifiers.Reviewer
import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.PullRequestEntity
import com.duxtinto.intellij.plugin.github.codereviews.presentation.pullrequestlist.PullRequestList
import javax.inject.Inject

class ReviewerPresenter
    @Inject
    constructor(
            @Reviewer pullRequestListView: PullRequestList.View,
            @Reviewer private val pullRequestListPresenter: PullRequestList.Presenter)
    : ReviewerContent.Presenter {

    init {
        pullRequestListPresenter.setView(pullRequestListView)
    }

    private lateinit var view: ReviewerContent.View

    override fun setView(view: ReviewerContent.View) {
        this.view = view
    }

    override fun presentPullRequests(pullRequests: List<PullRequestEntity>) {
        pullRequestListPresenter.displayPullRequests(pullRequests)
    }
}