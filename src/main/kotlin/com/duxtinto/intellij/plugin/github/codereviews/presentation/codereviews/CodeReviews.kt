package com.duxtinto.intellij.plugin.github.codereviews.presentation.codereviews

import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.PullRequestEntity
import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.reviews.CodeReviewEntity
import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.reviews.comments.CodeReviewCommentEntity
import javax.swing.*
import javax.swing.tree.TreeModel

interface CodeReviews {
    interface Model : TreeModel {
        fun setPullRequest(pullRequest: PullRequestEntity)
        fun setReviews(reviews: List<CodeReviewEntity>)
        fun setReviewComments(review: CodeReviewEntity, comments: List<CodeReviewCommentEntity>)
    }

    interface View {
        val content: JComponent
        fun render(model: CodeReviews.Model)
        interface Events {
            interface MouseListener: java.awt.event.MouseListener
        }
    }

    interface Presenter {
        fun setView(view: View)
        fun presentPullRequest(pullRequest: PullRequestEntity)
        fun presentReviews(reviews: List<CodeReviewEntity>)
        fun presentComments(review: CodeReviewEntity, comments: List<CodeReviewCommentEntity>)
    }
}
