package com.duxtinto.intellij.plugin.github.codereviews.presentation.reviewee

import com.duxtinto.intellij.plugin.github.codereviews.ide.acl.presentation.toolwindow.ToolWindowContent
import com.duxtinto.intellij.plugin.github.codereviews.presentation.codereviews.CodeReviews
import com.duxtinto.intellij.plugin.github.codereviews.presentation.pullrequestlist.PullRequestList
import com.intellij.openapi.ui.SimpleToolWindowPanel
import com.intellij.ui.OnePixelSplitter
import javax.inject.Inject

class RevieweeView
    @Inject
    constructor(
        private val pullRequestListView: PullRequestList.View,
        private val codeReviewsView: CodeReviews.View)
    : ToolWindowContent.Reviewee.View {

    override val content = SimpleToolWindowPanel(true, true).apply {
        setContent(
                OnePixelSplitter(false, 0.7f).apply {
                    firstComponent = pullRequestListView.content
                    secondComponent = codeReviewsView.content
                }
        )
    }

}
