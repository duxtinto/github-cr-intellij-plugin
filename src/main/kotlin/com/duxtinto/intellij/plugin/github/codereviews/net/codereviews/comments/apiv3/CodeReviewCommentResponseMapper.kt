package com.duxtinto.intellij.plugin.github.codereviews.net.codereviews.comments.apiv3

import com.duxtinto.intellij.plugin.github.codereviews.domain.DomainDataMapper
import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.reviews.comments.CodeReviewCommentEntity
import javax.inject.Inject

class CodeReviewCommentResponseMapper
    @Inject constructor()
    : DomainDataMapper<CodeReviewCommentEntity, CodeReviewCommentResponse> {

    override fun toEntity(dataModel: CodeReviewCommentResponse): CodeReviewCommentEntity {
        return with(dataModel) {
            CodeReviewCommentEntity(id, pull_request_review_id, body)
        }
    }
}