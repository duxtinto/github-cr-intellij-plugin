package com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow.codereviews.events

import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.reviews.CodeReviewEntity
import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.reviews.comments.CommentsByReviewInteractor
import com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow.codereviews.CodeReviews
import com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow.codereviews.CodeReviewsTree
import java.awt.event.MouseEvent
import javax.inject.Inject
import javax.swing.event.MouseInputAdapter
import javax.swing.tree.DefaultMutableTreeNode

class MouseListener
    @Inject
    constructor(
            private val getAllCommentsFor: CommentsByReviewInteractor,
            private val presenter: CodeReviews.Presenter)
    : MouseInputAdapter(), CodeReviews.View.Events.MouseListener {

    override fun mousePressed(event: MouseEvent) {
        if (event.clickCount == 2) {
            if (event.source is CodeReviewsTree) {
                val node = (event.source as CodeReviewsTree).lastSelectedPathComponent
                if (node is DefaultMutableTreeNode) {
                    when(node.userObject) {
                        is CodeReviewEntity -> {
                            with(node.userObject as CodeReviewEntity) {
                                presenter.presentComments(this, getAllCommentsFor.run(this)!!)
                            }
                        }
                    }
                }
            }

        }
    }
}
