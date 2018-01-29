package com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow.codereviews

import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.PullRequestEntity
import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.reviews.GetAllReviewsForInteractor
import javax.inject.Inject

class CodeReviewsPresenter
    @Inject
    constructor(private val getAllCodeReviewsFor: GetAllReviewsForInteractor)
    : CodeReviews.Presenter {

    private var view : CodeReviews.View? = null

    override fun setView(view: CodeReviews.View) {
        this.view = view
    }

    override fun displayCodeReviews(pullRequest: PullRequestEntity) {
        view?.render(CodeReviewsModel(getAllCodeReviewsFor.run(pullRequest)))
    }
}