package com.duxtinto.intellij.plugin.github.codereviews.ide.acl.events.repositories

import com.duxtinto.intellij.plugin.github.codereviews.di.qualifiers.Reviewee
import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.GetAllMyOpenForRepoInteractor
import com.duxtinto.intellij.plugin.github.codereviews.domain.repositories.FindGithubRepoForRootFolderInteractor
import com.duxtinto.intellij.plugin.github.codereviews.presentation.pullrequestlist.PullRequestList
import com.intellij.dvcs.repo.VcsRepositoryMappingListener
import javax.inject.Inject

class GitChangeListener
    @Inject
    constructor(
            private val githubRepoFinder: FindGithubRepoForRootFolderInteractor,
            private val pullRequestFetcher: GetAllMyOpenForRepoInteractor,
            @Reviewee private val pullRequestsPresenter: PullRequestList.Presenter)
    : VcsRepositoryMappingListener {

    override fun mappingChanged() {
        val repo = githubRepoFinder.run(Unit)
        if (repo != null) {
            pullRequestsPresenter.displayPullRequests(pullRequestFetcher.run(repo)!!)
        }
    }
}
