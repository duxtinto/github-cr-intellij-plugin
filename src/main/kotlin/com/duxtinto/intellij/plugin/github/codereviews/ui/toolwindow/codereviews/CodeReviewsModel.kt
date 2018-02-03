package com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow.codereviews

import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.reviews.CodeReviewEntity
import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.reviews.comments.CodeReviewCommentEntity
import javax.swing.tree.DefaultMutableTreeNode
import javax.swing.tree.DefaultTreeModel

class CodeReviewsModel
    : CodeReviews.Model, DefaultTreeModel(DefaultMutableTreeNode("Code Reviews")) {

    var mutableRoot: DefaultMutableTreeNode
        get() = super.root as DefaultMutableTreeNode
        set(value) = super.setRoot(value)

    override fun setReviews(reviews: List<CodeReviewEntity>) {
        val pullRequestIds = mutableSetOf<Long>()
        mutableRoot.removeAllChildren()
        for (review in reviews) {
            pullRequestIds.add(review.pull_request_id)
            insertNodeInto(DefaultMutableTreeNode(review), mutableRoot, mutableRoot.childCount)
        }
        mutableRoot.userObject = "Code Reviews for PR " + pullRequestIds.joinToString(", ", "#")
        this.reload()
    }

    override fun setReviewComments(review: CodeReviewEntity, comments: List<CodeReviewCommentEntity>) {
        for (node in super.root.children()) {
            with(node as DefaultMutableTreeNode) {
                if (userObject == review) {
                    removeAllChildren()
                    for (comment in comments) {
                        insertNodeInto(DefaultMutableTreeNode(comment), this, this.childCount)
                    }
                    reload()
                }
            }
        }
    }
}
