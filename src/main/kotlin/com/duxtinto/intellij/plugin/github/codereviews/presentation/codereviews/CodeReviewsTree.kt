package com.duxtinto.intellij.plugin.github.codereviews.presentation.codereviews

import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.PullRequestEntity
import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.reviews.CodeReviewEntity
import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.reviews.comments.CodeReviewCommentEntity
import com.intellij.ui.treeStructure.Tree
import javax.swing.tree.DefaultMutableTreeNode
import javax.swing.tree.TreeModel
import javax.swing.tree.TreeNode

class CodeReviewsTree : Tree {
    constructor() : super()
    constructor(root: TreeNode) : super(root)
    constructor(model: TreeModel) : super(model)

    override fun convertValueToText(value: Any?, selected: Boolean, expanded: Boolean, leaf: Boolean, row: Int, hasFocus: Boolean): String {
        with(getUserObject(value)) {
            return when (this) {
                is PullRequestEntity -> "Code Reviews for PR #${this.number}"
                is CodeReviewEntity -> "${this.reviewer.username} [${this.state}]"
                is CodeReviewCommentEntity -> this.body
                else -> { super.convertValueToText(value, selected, expanded, leaf, row, hasFocus) }
            }
        }
    }

    fun getUserObject(node: Any?): Any? {
        return if (node is DefaultMutableTreeNode) node.userObject else node
    }
}