package com.duxtinto.intellij.plugin.github.codereviews.net.codereviews.apiv3

import com.duxtinto.intellij.plugin.github.codereviews.domain.DomainDataMapper
import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.reviews.CodeReviewEntity
import javax.inject.Inject

class CodeReviewResponseMapper
    @Inject
    constructor(val reviewerMapper: CodeReviewerMapper)
    : DomainDataMapper<CodeReviewEntity, CodeReviewResponse> {
    override fun toEntity(dataModel: CodeReviewResponse): CodeReviewEntity {
        return CodeReviewEntity(
                id = dataModel.id,
                reviewer = reviewerMapper.toEntity(dataModel.user),
                state = dataModel.state,
                pull_request_id = getPullRequestIdFromLink(dataModel._links.pull_request)
        )
    }

    private fun getPullRequestIdFromLink(link: Map<String, String>): Long {
        return Regex("^(.*)/(?<id>\\d.*)\$").matchEntire(link["href"]!!)!!.groups[2]!!.value.toLong()
    }
}