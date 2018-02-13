package com.duxtinto.intellij.plugin.github.codereviews.ide.acl.presentation.toolwindow.toolbar.reviewee

import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.GetAllMyOpenForRepoInteractor
import com.duxtinto.intellij.plugin.github.codereviews.domain.repositories.FindGithubRepoForRootFolderInteractor
import com.duxtinto.intellij.plugin.github.codereviews.presentation.reviewee.RevieweeContent
import com.intellij.icons.AllIcons
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.project.DumbAwareAction
import javax.inject.Inject

class RefreshPullRequestListAction
    @Inject
    constructor(
            private val githubRepoFinder: FindGithubRepoForRootFolderInteractor,
            private val pullRequestFetcher: GetAllMyOpenForRepoInteractor,
            private val revieweePresenter: RevieweeContent.Presenter)
    : DumbAwareAction("Refresh", "Refresh pull request list", AllIcons.Actions.Refresh) {
    override fun actionPerformed(e: AnActionEvent) {
        githubRepoFinder.run(Unit)?.let {
            pullRequestFetcher.run(it)?.let {
                revieweePresenter.presentPullRequests(it)
            }
        }
    }
}