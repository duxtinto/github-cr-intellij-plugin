package com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow.codereviews

import javax.swing.*
import javax.swing.tree.TreeModel

interface CodeReviews {
    interface Model : TreeModel

    interface View {
        val content: JComponent
    }

    interface Presenter {
        fun setView(view: View)
    }
}
