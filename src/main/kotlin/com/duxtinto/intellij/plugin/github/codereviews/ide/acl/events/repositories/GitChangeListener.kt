package com.duxtinto.intellij.plugin.github.codereviews.ide.acl.events.repositories

import com.duxtinto.intellij.plugin.github.codereviews.di.qualifiers.Reviewee
import com.duxtinto.intellij.plugin.github.codereviews.di.qualifiers.Reviewer
import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.PullRequestsByRepositoryInteractor
import com.duxtinto.intellij.plugin.github.codereviews.domain.repositories.FindGithubRepoForRootFolderInteractor
import com.duxtinto.intellij.plugin.github.codereviews.presentation.reviewee.RevieweeContent
import com.duxtinto.intellij.plugin.github.codereviews.presentation.reviewer.ReviewerContent
import com.intellij.dvcs.repo.VcsRepositoryMappingListener
import javax.inject.Inject

class GitChangeListener
    @Inject
    constructor(
            private val githubRepoFinder: FindGithubRepoForRootFolderInteractor,
            @Reviewee private val assignedPRInteractor: PullRequestsByRepositoryInteractor,
            @Reviewer private val requestedPRInteractor: PullRequestsByRepositoryInteractor,
            private val revieweePresenter: RevieweeContent.Presenter,
            private val reviewerPresenter: ReviewerContent.Presenter)
    : VcsRepositoryMappingListener {

    override fun mappingChanged() {
        val repo = githubRepoFinder.run(Unit)
        if (repo != null) {
            revieweePresenter.presentPullRequests(assignedPRInteractor.run(repo)!!)
            reviewerPresenter.presentPullRequests(requestedPRInteractor.run(repo)!!)
        }
    }
}
