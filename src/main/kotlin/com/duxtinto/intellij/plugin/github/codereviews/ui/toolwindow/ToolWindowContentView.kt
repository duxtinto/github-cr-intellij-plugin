package com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow

import com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow.codereviews.CodeReviews
import com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow.pullrequestlist.PullRequestList
import com.intellij.openapi.ui.SimpleToolWindowPanel
import com.intellij.ui.OnePixelSplitter

class ToolWindowContentView(
        private val pullRequestListView: PullRequestList.View,
        private val codeReviewsView: CodeReviews.View) :
        ToolWindowContent.View {

    override val content = SimpleToolWindowPanel(true, true).apply {
        setContent(
                OnePixelSplitter(false, 0.7f).apply {
                    firstComponent = pullRequestListView.content
                    secondComponent = codeReviewsView.content
                }
        )
    }

}
