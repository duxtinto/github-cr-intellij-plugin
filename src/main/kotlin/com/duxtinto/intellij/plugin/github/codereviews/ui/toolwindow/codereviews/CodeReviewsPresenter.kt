package com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow.codereviews

import javax.inject.Inject

class CodeReviewsPresenter
    @Inject constructor()
    : CodeReviews.Presenter {

    private var model : CodeReviews.Model = CodeReviewsModel()
    private var view : CodeReviews.View? = null

    override fun setView(view: CodeReviews.View) {
        this.view = view
    }
}