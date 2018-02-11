package com.duxtinto.intellij.plugin.github.codereviews.presentation.reviewer

import com.intellij.openapi.ui.SimpleToolWindowPanel
import javax.inject.Inject

class ReviewerView
    @Inject
    constructor()
    : ReviewerContent.View {
    override val content = SimpleToolWindowPanel(true, true)
}
