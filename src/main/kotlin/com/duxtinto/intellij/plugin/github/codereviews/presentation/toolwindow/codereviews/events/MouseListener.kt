package com.duxtinto.intellij.plugin.github.codereviews.presentation.toolwindow.codereviews.events

import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.ActionOnPullRequestInteractor
import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.PullRequestEntity
import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.reviews.CodeReviewEntity
import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.reviews.comments.ActionOnReviewCommentInteractor
import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.reviews.comments.CodeReviewCommentEntity
import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.reviews.comments.CommentsByReviewInteractor
import com.duxtinto.intellij.plugin.github.codereviews.presentation.toolwindow.codereviews.CodeReviews
import com.duxtinto.intellij.plugin.github.codereviews.presentation.toolwindow.codereviews.CodeReviewsTree
import java.awt.event.MouseEvent
import javax.inject.Inject
import javax.inject.Named
import javax.swing.event.MouseInputAdapter
import javax.swing.tree.DefaultMutableTreeNode

class MouseListener
    @Inject
    constructor(
            private val getAllCommentsFor: CommentsByReviewInteractor,
            private val presenter: CodeReviews.Presenter,
            @Named("goToLine") private val goToCommentLine: ActionOnReviewCommentInteractor,
            private val checkoutBranchInteractor: ActionOnPullRequestInteractor)
    : MouseInputAdapter(), CodeReviews.View.Events.MouseListener {

    override fun mousePressed(event: MouseEvent) {
        if (event.clickCount != 2) {
            return
        }
        val tree = event.source as? CodeReviewsTree ?: return
        val selectedNode = tree.lastSelectedPathComponent as? DefaultMutableTreeNode ?: return
        when(selectedNode.userObject) {
            is CodeReviewEntity -> {
                with(selectedNode.userObject as CodeReviewEntity) {
                    presenter.presentComments(this, getAllCommentsFor.run(this)!!)
                }
            }

            is CodeReviewCommentEntity -> {
                val rootNode = tree.model.root as DefaultMutableTreeNode
                with(selectedNode.userObject as CodeReviewCommentEntity) {
                    if (rootNode.userObject is PullRequestEntity) {
                        checkoutBranchInteractor.run(rootNode.userObject as PullRequestEntity)
                    }
                    goToCommentLine.run(this)
                }
            }
        }
    }
}
