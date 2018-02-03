package com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow.codereviews

import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.reviews.CodeReviewEntity
import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.reviews.comments.CodeReviewCommentEntity
import com.intellij.ui.treeStructure.Tree
import com.intellij.util.ui.tree.TreeUtil
import javax.swing.tree.TreeModel
import javax.swing.tree.TreeNode

class CodeReviewsTree : Tree {
    constructor() : super()
    constructor(root: TreeNode) : super(root)
    constructor(model: TreeModel) : super(model)

    override fun convertValueToText(value: Any?, selected: Boolean, expanded: Boolean, leaf: Boolean, row: Int, hasFocus: Boolean): String {
        with(TreeUtil.getUserObject(value)) {
            return when (this) {
                is CodeReviewEntity -> "${this.reviewer.username} [${this.state}]"
                is CodeReviewCommentEntity -> this.body
                else -> { super.convertValueToText(value, selected, expanded, leaf, row, hasFocus) }
            }
        }
    }
}