package com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow.codereviews

import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.reviews.CodeReviewEntity
import com.intellij.ide.util.treeView.NodeRenderer
import com.intellij.util.ui.tree.TreeUtil
import javax.inject.Inject
import javax.swing.JTree

class CodeReviewsTreeCellRenderer
    @Inject constructor()
    : NodeRenderer() {
    override fun customizeCellRenderer(tree: JTree, value: Any?, selected: Boolean, expanded: Boolean, leaf: Boolean, row: Int, hasFocus: Boolean) {
        with(TreeUtil.getUserObject(value)) {
            if (this is CodeReviewEntity) {
                return super.customizeCellRenderer(tree, "${this.reviewer.username} [${this.state}]", selected, expanded, leaf, row, hasFocus)
            }
        }

        return super.customizeCellRenderer(tree, value, selected, expanded, leaf, row, hasFocus)
    }
}