package com.duxtinto.intellij.plugin.github.codereviews.net.codereviews.apiv3

import com.duxtinto.intellij.plugin.github.codereviews.domain.DomainDataMapper
import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.reviews.CodeReviewerEntity
import javax.inject.Inject

class CodeReviewerMapper
    @Inject
    constructor()
    : DomainDataMapper<CodeReviewerEntity, UserResponse> {
    override fun toEntity(dataModel: UserResponse): CodeReviewerEntity {
        return CodeReviewerEntity(
                id = dataModel.id,
                username = dataModel.login
        )
    }
}