package com.duxtinto.intellij.plugin.github.codereviews.domain.repositories.git

import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.ActionOnPullRequestInteractor
import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.PullRequestEntity
import com.duxtinto.intellij.plugin.github.codereviews.domain.repositories.RepositoriesDomainContract
import com.duxtinto.intellij.plugin.github.codereviews.domain.repositories.RepositoriesDomainContract.Git
import javax.inject.Inject

class PullBranchInteractor
    @Inject
    constructor(
            private val branchOperator: Git.BranchOperator,
            private val branchFetcher: Git.Fetcher,
            private val gitRepositoryFinder: RepositoriesDomainContract.Git.RepositoryFinder)
    : ActionOnPullRequestInteractor {
    override fun run(request: PullRequestEntity) {
        val repository = gitRepositoryFinder.findRootRepo()
        if (repository != null) {
            branchFetcher.fetchAll(repository)
            branchOperator.checkOut(request.head?.sha!!, repository)
        }
    }
}
