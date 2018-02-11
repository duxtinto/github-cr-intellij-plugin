package com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests

import com.duxtinto.intellij.plugin.github.codereviews.domain.repositories.GithubRepositoryEntity
import javax.inject.Inject

class GetAllMyOpenForRepoInteractor
    @Inject
    constructor(private val repository: PullRequestDomainContract.Repository)
    : PullRequestsByRepositoryInteractor {

    override fun run(request: GithubRepositoryEntity): List<PullRequestEntity> {
        return repository.getAllMyOpenBy(request.ownerName, request.name)
    }
}
