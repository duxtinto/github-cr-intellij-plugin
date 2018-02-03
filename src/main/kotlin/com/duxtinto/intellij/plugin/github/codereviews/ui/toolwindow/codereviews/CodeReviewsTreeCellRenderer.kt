package com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow.codereviews

import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.reviews.CodeReviewEntity
import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.reviews.comments.CodeReviewCommentEntity
import com.intellij.ide.util.treeView.NodeRenderer
import com.intellij.util.ui.tree.TreeUtil
import javax.inject.Inject
import javax.swing.JTree

class CodeReviewsTreeCellRenderer
    @Inject constructor()
    : NodeRenderer() {
    override fun customizeCellRenderer(tree: JTree, value: Any?, selected: Boolean, expanded: Boolean, leaf: Boolean, row: Int, hasFocus: Boolean) {
        with(TreeUtil.getUserObject(value)) {
            val body = when (this) {
                is CodeReviewEntity -> "${this.reviewer.username} [${this.state}]"
                is CodeReviewCommentEntity -> this.body
                else -> value
            }
            return super.customizeCellRenderer(tree, body, selected, expanded, leaf, row, hasFocus)
        }

    }
}