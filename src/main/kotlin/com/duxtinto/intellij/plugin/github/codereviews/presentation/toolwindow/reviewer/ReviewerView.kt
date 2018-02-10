package com.duxtinto.intellij.plugin.github.codereviews.presentation.toolwindow.reviewer

import com.duxtinto.intellij.plugin.github.codereviews.presentation.toolwindow.ToolWindowContent
import com.intellij.openapi.ui.SimpleToolWindowPanel
import javax.inject.Inject

class ReviewerView
    @Inject
    constructor()
    : ToolWindowContent.Reviewer.View {
    override val content = SimpleToolWindowPanel(true, true)
}
