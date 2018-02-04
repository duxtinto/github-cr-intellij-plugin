package com.duxtinto.intellij.plugin.github.codereviews.events.repos

import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.GetAllOpenForRepoInteractor
import com.duxtinto.intellij.plugin.github.codereviews.domain.repositories.GithubRepositoryEntity
import com.duxtinto.intellij.plugin.github.codereviews.domain.repositories.FindGithubRepoForRootFolderInteractor
import com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow.pullrequestlist.PullRequestList
import com.intellij.dvcs.repo.VcsRepositoryMappingListener
import javax.inject.Inject

import com.google.common.base.Preconditions.checkNotNull

class GitChangeListener
    @Inject
    constructor(
        private val githubRepoFinder: FindGithubRepoForRootFolderInteractor,
        private val pullRequestFetcher: GetAllOpenForRepoInteractor,
        private val pullRequestsPresenter: PullRequestList.Presenter)
    : VcsRepositoryMappingListener {

    override fun mappingChanged() {
        val repo = githubRepoFinder.run(Unit)
        if (repo != null) {
            pullRequestsPresenter.displayPullRequests(pullRequestFetcher.run(repo)!!)
        }
    }
}
