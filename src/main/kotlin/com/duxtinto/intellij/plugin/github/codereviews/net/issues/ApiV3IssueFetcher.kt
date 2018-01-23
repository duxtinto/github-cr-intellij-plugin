package com.duxtinto.intellij.plugin.github.codereviews.net.issues

import com.duxtinto.intellij.plugin.github.codereviews.domain.issues.IssueEntity
import com.duxtinto.intellij.plugin.github.codereviews.domain.issues.IssuesDomainContract
import com.duxtinto.intellij.plugin.github.codereviews.domain.repositories.FindGithubRepoForRootFolderInteractor
import com.duxtinto.intellij.plugin.github.codereviews.ide.acl.services.issues.GithubApiV3IssueLoader
import javax.inject.Inject

class ApiV3IssueFetcher
@Inject
constructor(
        private val githubRootRepoFinder : FindGithubRepoForRootFolderInteractor,
        private val apiV3Loader: GithubApiV3IssueLoader)
    : IssuesDomainContract.Fetcher {
    override fun fetchIssueById(id: Long): IssueEntity? {
        return githubRootRepoFinder.run(null)?.let { apiV3Loader.loadIssueById(it, id) }
    }
}