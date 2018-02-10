package com.duxtinto.intellij.plugin.github.codereviews.net.codereviews.comments.apiv3

data class CodeReviewCommentResponse(
        val id: Long,
        val pull_request_review_id: Long,
        val body: String,
        val diff_hunk: String,
        val path: String,
        val position: Int?,
        val original_position: Int,
        val commit_id: String,
        val original_commit_id: String
)