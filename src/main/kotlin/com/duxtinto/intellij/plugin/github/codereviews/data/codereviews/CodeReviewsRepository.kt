package com.duxtinto.intellij.plugin.github.codereviews.data.codereviews

import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.PullRequestEntity
import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.reviews.CodeReviewEntity
import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.reviews.CodeReviewsDomainContract
import javax.inject.Inject

class CodeReviewsRepository
    @Inject
    constructor(private val fetcher: CodeReviewsDataContract.Fetcher)
    : CodeReviewsDomainContract.Repository {
    override fun findAllBy(pullRequest: PullRequestEntity): List<CodeReviewEntity> {
        return fetcher.fetchAllByPullRequestId(pullRequest.number)
    }
}