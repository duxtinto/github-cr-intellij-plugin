package com.duxtinto.intellij.plugin.github.codereviews.ide.acl.services.git

import com.duxtinto.intellij.plugin.github.codereviews.domain.repositories.RepositoriesDomainContract
import git4idea.repo.GitRepository
import git4idea.update.GitFetcher

class IdeaFetcher(private val gitFetcher: GitFetcher)
    : RepositoriesDomainContract.Git.Fetcher
{
    override fun fetchAll(repository: RepositoriesDomainContract.Git.Repository): Boolean {
        return gitFetcher.fetchRootsAndNotify(mutableSetOf(repository as GitRepository), "Error while fetching remote branches", false)
    }
}