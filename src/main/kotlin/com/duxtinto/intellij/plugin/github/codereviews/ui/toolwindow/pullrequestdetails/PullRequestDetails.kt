package com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow.pullrequestdetails

import javax.swing.*
import javax.swing.tree.TreeModel

interface PullRequestDetails {
    interface Model : TreeModel

    interface View {
        val content: JComponent
    }

    interface Presenter {
        val model: Model
        fun setView(view: View)
    }
}
