package com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow

import javax.swing.*

interface ToolWindowContent {
    interface Model
    interface View {
        val content: JComponent
    }

    interface Presenter {
        fun setView(view: View)
        fun displayContent()
    }
}
