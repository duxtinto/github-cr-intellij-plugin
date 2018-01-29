package com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.reviews

import com.duxtinto.intellij.plugin.github.codereviews.domain.Interactor
import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.PullRequestEntity
import javax.inject.Inject

class GetAllReviewsForInteractor
    @Inject
    constructor(private val repository: CodeReviewsDomainContract.Repository)
    : Interactor<PullRequestEntity, List<CodeReviewEntity>> {
    override fun run(pullRequest: PullRequestEntity): List<CodeReviewEntity> {
        return repository.findAllBy(pullRequest)
    }
}