package com.duxtinto.intellij.plugin.github.codereviews.presentation.toolwindow

import com.duxtinto.intellij.plugin.github.codereviews.domain.repositories.FindGithubRepoForRootFolderInteractor
import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.GetAllOpenForRepoInteractor
import com.intellij.ui.content.ContentFactory
import com.intellij.ui.content.ContentManager
import javax.inject.Inject
import javax.inject.Named

class ToolWindowContentPresenter
    @Inject
    constructor(
            private val githubRepoFinder: FindGithubRepoForRootFolderInteractor,
            private val pullRequestFetcher: GetAllOpenForRepoInteractor,
            private val contentFactory: ContentFactory,
            @Named("GH_Reviews") private val contentManager: ContentManager,
            private val revieweeView: ToolWindowContent.Reviewee.View,
            private val revieweePresenter: ToolWindowContent.Reviewee.Presenter,
            private val reviewerView: ToolWindowContent.Reviewer.View,
            reviewerPresenter: ToolWindowContent.Reviewer.Presenter)
    : ToolWindowContent.Presenter {

    init {
        revieweePresenter.setView(revieweeView)
        reviewerPresenter.setView(reviewerView)
    }

    override fun displayToolWindow() {
        contentManager.addContent(contentFactory.createContent(revieweeView.content, "Reviewee", false))
        contentManager.addContent(contentFactory.createContent(reviewerView.content, "Reviewer", false))
        presentPullRequests()
    }

    private fun presentPullRequests() {
        githubRepoFinder.run(Unit)?.let {
            pullRequestFetcher.run(it)?.let {
                revieweePresenter.presentPullRequests(it)
            }
        }
    }
}

