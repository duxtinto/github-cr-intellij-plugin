package com.duxtinto.intellij.plugin.github.codereviews.data.codereviews

import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.reviews.CodeReviewEntity
import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.reviews.comments.CodeReviewCommentEntity

interface CodeReviewsDataContract {
    interface Fetcher {
        fun fetchAllByPullRequestId(id: Long): List<CodeReviewEntity>
        fun fetchAllReviewComments(review: CodeReviewEntity): List<CodeReviewCommentEntity>
    }
}