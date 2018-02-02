package com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.reviews

import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.PullRequestEntity
import javax.inject.Inject

class GetAllReviewsForInteractor
    @Inject
    constructor(private val repository: CodeReviewsDomainContract.Repository)
    : CodeReviewsByPullRequestInteractor {
    override fun run(request: PullRequestEntity): List<CodeReviewEntity> {
        return repository.findAllBy(request)
    }
}