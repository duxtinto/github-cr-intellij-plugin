package com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests

import com.duxtinto.intellij.plugin.github.codereviews.domain.repositories.GithubRepositoryEntity
import java.io.IOException
import javax.inject.Inject

class GetAllOpenForRepoInteractor
    @Inject
    constructor(private val repository: PullRequestDomainContract.Repository)
    : PullRequestsByRepositoryInteractor {

    override fun run(request: GithubRepositoryEntity): List<PullRequestEntity>? {
        try {
            return repository.getAllOpenBy(request.ownerName, request.name)
        } catch (e: IOException) {
            e.printStackTrace()
            throw RuntimeException(e)
        }

    }
}
