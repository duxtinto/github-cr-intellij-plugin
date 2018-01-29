package com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow.codereviews

import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.reviews.CodeReviewEntity
import javax.swing.tree.DefaultMutableTreeNode
import javax.swing.tree.DefaultTreeModel

class CodeReviewsModel(codeReviews: List<CodeReviewEntity> = listOf())
    : CodeReviews.Model, DefaultTreeModel(DefaultMutableTreeNode("Code Reviews")) {

    var mutableRoot: DefaultMutableTreeNode
        get() = super.root as DefaultMutableTreeNode
        set(value) = super.setRoot(value)

    init {
        for (review in codeReviews) {
            insertNodeInto(DefaultMutableTreeNode(review), mutableRoot, mutableRoot.childCount)
        }
    }
}
