package com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow

import com.intellij.ui.content.ContentFactory
import com.intellij.ui.content.ContentManager
import javax.inject.Inject
import javax.inject.Named

class ToolWindowContentPresenter
    @Inject
    constructor(
            private val contentFactory: ContentFactory,
            @Named("GH_Reviews") private val contentManager: ContentManager)
    : ToolWindowContent.Presenter {
    private lateinit var revieweeView: ToolWindowContent.RevieweeView
    private lateinit var reviewerView: ToolWindowContent.ReviewerView

    override fun setRevieweeView(view: ToolWindowContent.RevieweeView) {
        revieweeView = view
        contentManager.addContent(contentFactory.createContent(view.content, "Reviewee", false))
    }

    override fun setReviewerView(view: ToolWindowContent.ReviewerView) {
        reviewerView = view
        contentManager.addContent(contentFactory.createContent(reviewerView.content, "Reviewer", false))
    }
}
