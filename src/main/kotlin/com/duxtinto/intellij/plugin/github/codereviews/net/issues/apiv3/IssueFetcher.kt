package com.duxtinto.intellij.plugin.github.codereviews.net.issues.apiv3

import com.duxtinto.intellij.plugin.github.codereviews.domain.issues.IssueEntity
import com.duxtinto.intellij.plugin.github.codereviews.domain.issues.IssuesDomainContract
import com.duxtinto.intellij.plugin.github.codereviews.domain.repositories.FindGithubRepoForRootFolderInteractor
import com.duxtinto.intellij.plugin.github.codereviews.ide.acl.services.github.issues.IdeaIssueLoader
import javax.inject.Inject

class IssueFetcher
    @Inject
    constructor(
        private val githubRootRepoFinder : FindGithubRepoForRootFolderInteractor,
        private val apiV3Loader: IdeaIssueLoader)
    : IssuesDomainContract.Fetcher {
    override fun fetchIssueById(id: Long): IssueEntity? {
        return githubRootRepoFinder.run(Unit)?.let { apiV3Loader.loadIssueById(it, id) }
    }
}
