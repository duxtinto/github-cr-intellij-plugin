package com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.reviews.comments

data class CodeReviewCommentEntity (
        val id: Long,
        val reviewId: Long,
        val body: String = ""
)