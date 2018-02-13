package com.duxtinto.intellij.plugin.github.codereviews.presentation.reviewer

import javax.swing.JComponent

interface ReviewerContent {
    interface View {
        val content: JComponent
    }

    interface Presenter {
        fun setView(view: View)
    }
}
