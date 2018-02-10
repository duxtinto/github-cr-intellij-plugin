package com.duxtinto.intellij.plugin.github.codereviews.presentation.toolwindow.reviewer

import com.duxtinto.intellij.plugin.github.codereviews.presentation.toolwindow.ToolWindowContent
import javax.inject.Inject

class ReviewerPresenter
    @Inject
    constructor()
    : ToolWindowContent.Reviewer.Presenter {

    private lateinit var view: ToolWindowContent.Reviewer.View

    override fun setView(view: ToolWindowContent.Reviewer.View) {
        this.view = view
    }
}