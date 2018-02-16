package com.duxtinto.intellij.plugin.github.codereviews.presentation.reviewer

import com.duxtinto.intellij.plugin.github.codereviews.di.qualifiers.Reviewer
import com.duxtinto.intellij.plugin.github.codereviews.ide.acl.presentation.toolwindow.toolbar.ActionToolbarExt
import com.duxtinto.intellij.plugin.github.codereviews.presentation.Toolbar
import com.duxtinto.intellij.plugin.github.codereviews.presentation.pullrequestlist.PullRequestList
import com.intellij.openapi.ui.SimpleToolWindowPanel
import javax.inject.Inject

class ReviewerView
    @Inject
    constructor(
            @Reviewer private val pullRequestListView: PullRequestList.View,
            @Reviewer toolbar: Toolbar
    )
    : ReviewerContent.View {

    override val content = SimpleToolWindowPanel(true, true).apply {
        setContent(pullRequestListView.content)
    }

    init {
        if (toolbar is ActionToolbarExt) {
            toolbar.setTargetComponent(pullRequestListView.content)
            content.setToolbar(toolbar.component)
        }
    }
}
