package com.duxtinto.intellij.plugin.github.codereviews.presentation.reviewee

import com.duxtinto.intellij.plugin.github.codereviews.di.qualifiers.Reviewee
import com.duxtinto.intellij.plugin.github.codereviews.ide.acl.presentation.toolwindow.toolbar.ActionToolbarExt
import com.duxtinto.intellij.plugin.github.codereviews.presentation.Toolbar
import com.duxtinto.intellij.plugin.github.codereviews.presentation.codereviews.CodeReviews
import com.duxtinto.intellij.plugin.github.codereviews.presentation.pullrequestlist.PullRequestList
import com.intellij.openapi.ui.SimpleToolWindowPanel
import com.intellij.ui.OnePixelSplitter
import javax.inject.Inject

class RevieweeView
    @Inject
    constructor(
        @Reviewee private val pullRequestListView: PullRequestList.View,
        private val codeReviewsView: CodeReviews.View,
        @Reviewee toolbar: Toolbar)
    : RevieweeContent.View {

    private lateinit var splitter: OnePixelSplitter

    override val content = SimpleToolWindowPanel(true, true).apply {
        splitter = OnePixelSplitter(false, 0.7f).apply {
            firstComponent = pullRequestListView.content
            secondComponent = codeReviewsView.content
        }

        setContent(splitter)
    }

    init {
        if (toolbar is ActionToolbarExt) {
            toolbar.setTargetComponent(splitter.firstComponent)
            content.setToolbar(toolbar.component)
        }
    }
}
