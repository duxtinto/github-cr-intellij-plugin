package com.duxtinto.intellij.plugin.github.codereviews.domain.repositories

import com.duxtinto.intellij.plugin.github.codereviews.domain.Interactor
import javax.inject.Inject

class FindGithubRepoForRootFolderInteractor
    @Inject
    constructor(
            private val gitRepositoryFinder: RepositoriesDomainContract.Git.RepositoryFinder,
            private val githubRepositoryFinder: RepositoriesDomainContract.GithubRepositoryFinder)
    : Interactor<Unit, GithubRepositoryEntity> {

    override fun run(request: Unit): GithubRepositoryEntity? {
        val rootGitRepo = gitRepositoryFinder.findRootRepo() ?: return null
        return githubRepositoryFinder.find(rootGitRepo)
    }
}
