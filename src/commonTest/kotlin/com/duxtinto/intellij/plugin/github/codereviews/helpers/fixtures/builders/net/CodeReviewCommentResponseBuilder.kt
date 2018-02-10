package com.duxtinto.intellij.plugin.github.codereviews.helpers.fixtures.builders.net

import com.duxtinto.intellij.plugin.github.codereviews.helpers.fixtures.FixtureBuilder
import com.duxtinto.intellij.plugin.github.codereviews.helpers.random.RandomGenerator
import com.duxtinto.intellij.plugin.github.codereviews.net.codereviews.comments.apiv3.CodeReviewCommentResponse

class CodeReviewCommentResponseBuilder : FixtureBuilder<CodeReviewCommentResponse> {
    private var original_position: Int = RandomGenerator.next()
    private var position: Int? = original_position

    private var original_commit_id: String = RandomGenerator.next()
    private var commit_id: String = original_commit_id

    override fun build(): CodeReviewCommentResponse {
        return CodeReviewCommentResponse(
                id = RandomGenerator.next(),
                pull_request_review_id = RandomGenerator.next(),
                body = RandomGenerator.next(),
                diff_hunk = RandomGenerator.next(),
                path = RandomGenerator.next(),
                position = position,
                original_position = original_position,
                commit_id = commit_id,
                original_commit_id = original_commit_id)
    }

    fun withPosition(position: Int?): CodeReviewCommentResponseBuilder {
        this.position = position
        return this
    }
}