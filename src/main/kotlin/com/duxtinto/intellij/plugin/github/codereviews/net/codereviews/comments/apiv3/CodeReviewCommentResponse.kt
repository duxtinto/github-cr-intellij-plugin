package com.duxtinto.intellij.plugin.github.codereviews.net.codereviews.comments.apiv3

data class CodeReviewCommentResponse(
        val id: Long,
        val pull_request_review_id: Long,
        val body: String
)