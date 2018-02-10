package com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow.codereviews

import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.PullRequestEntity
import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.reviews.CodeReviewEntity
import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.reviews.comments.CodeReviewCommentEntity
import javax.swing.tree.DefaultMutableTreeNode
import javax.swing.tree.DefaultTreeModel

class CodeReviewsModel
    : CodeReviews.Model, DefaultTreeModel(DefaultMutableTreeNode("Code Reviews")) {
    var mutableRoot: DefaultMutableTreeNode
        get() = super.root as DefaultMutableTreeNode
        set(value) = super.setRoot(value)

    override fun setPullRequest(pullRequest: PullRequestEntity) {
        mutableRoot.removeAllChildren()
        mutableRoot.userObject = pullRequest
        this.reload()
    }

    override fun setReviews(reviews: List<CodeReviewEntity>) {
        mutableRoot.removeAllChildren()
        for (review in reviews) {
            insertNodeInto(DefaultMutableTreeNode(review), mutableRoot, mutableRoot.childCount)
        }
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
