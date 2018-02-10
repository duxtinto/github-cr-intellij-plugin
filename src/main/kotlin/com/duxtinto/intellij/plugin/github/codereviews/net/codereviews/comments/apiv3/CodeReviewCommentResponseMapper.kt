package com.duxtinto.intellij.plugin.github.codereviews.net.codereviews.comments.apiv3

import com.duxtinto.intellij.plugin.github.codereviews.domain.DomainDataMapper
import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.reviews.comments.CodeReviewCommentEntity
import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.reviews.comments.CodeReviewCommentEntity.State as EntityState
import javax.inject.Inject

class CodeReviewCommentResponseMapper
    @Inject constructor()
    : DomainDataMapper<CodeReviewCommentEntity, CodeReviewCommentResponse> {

    override fun toEntity(dataModel: CodeReviewCommentResponse): CodeReviewCommentEntity {
        return with(dataModel) {
            CodeReviewCommentEntity(
                    id = id,
                    reviewId = pull_request_review_id,
                    body = body,
                    filePath = path,
                    lineNumber = position ?: 0,
                    state = if (position == null) { EntityState.OUTDATED } else { EntityState.ACTIVE }
            )
        }
    }
}