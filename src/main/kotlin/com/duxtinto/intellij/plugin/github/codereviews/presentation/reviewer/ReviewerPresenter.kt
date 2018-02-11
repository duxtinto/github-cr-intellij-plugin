package com.duxtinto.intellij.plugin.github.codereviews.presentation.reviewer

import javax.inject.Inject

class ReviewerPresenter
    @Inject
    constructor()
    : ReviewerContent.Presenter {

    private lateinit var view: ReviewerContent.View

    override fun setView(view: ReviewerContent.View) {
        this.view = view
    }
}