package com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow

import javax.swing.*

interface ToolWindowContent {
    interface Model
    interface RevieweeView {
        val content: JComponent
    }

    interface ReviewerView {
        val content: JComponent
    }

    interface Presenter {
        fun setRevieweeView(view: RevieweeView)
        fun setReviewerView(view: ReviewerView)
    }
}
