package com.duxtinto.intellij.plugin.github.codereviews.presentation.codereviews

import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.PullRequestEntity
import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.reviews.CodeReviewEntity
import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.reviews.comments.CodeReviewCommentEntity
import com.duxtinto.intellij.plugin.github.codereviews.presentation.Formatter
import com.intellij.ide.util.treeView.NodeRenderer
import com.intellij.util.ui.tree.TreeUtil
import javax.inject.Inject
import javax.swing.JTree

class CodeReviewsTreeCellRenderer
    @Inject
    constructor(private val commentFormatter: @JvmSuppressWildcards Formatter<CodeReviewCommentEntity, String>)
    : NodeRenderer() {
    override fun customizeCellRenderer(tree: JTree, value: Any?, selected: Boolean, expanded: Boolean, leaf: Boolean, row: Int, hasFocus: Boolean) {
        with(TreeUtil.getUserObject(value)) {
            val body = when (this) {
                is PullRequestEntity -> "Code Reviews for PR #${this.number}"
                is CodeReviewEntity -> "${this.reviewer.username} [${this.state}]"
                is CodeReviewCommentEntity -> commentFormatter.format(this)
                else -> value
            }
            return super.customizeCellRenderer(tree, body, selected, expanded, leaf, row, hasFocus)
        }

    }
}