package com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow.codereviews

import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.PullRequestEntity
import javax.swing.*
import javax.swing.tree.TreeModel

interface CodeReviews {
    interface Model : TreeModel

    interface View {
        val content: JComponent
        fun render(model: CodeReviews.Model)
    }

    interface Presenter {
        fun setView(view: View)
        fun displayCodeReviews(pullRequest: PullRequestEntity)
    }
}
