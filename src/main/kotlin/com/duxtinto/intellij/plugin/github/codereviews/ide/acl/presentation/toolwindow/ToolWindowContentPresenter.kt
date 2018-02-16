package com.duxtinto.intellij.plugin.github.codereviews.ide.acl.presentation.toolwindow

import com.duxtinto.intellij.plugin.github.codereviews.di.qualifiers.Reviewee
import com.duxtinto.intellij.plugin.github.codereviews.di.qualifiers.Reviewer
import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.PullRequestsByRepositoryInteractor
import com.duxtinto.intellij.plugin.github.codereviews.domain.repositories.FindGithubRepoForRootFolderInteractor
import com.duxtinto.intellij.plugin.github.codereviews.presentation.reviewee.RevieweeContent
import com.duxtinto.intellij.plugin.github.codereviews.presentation.reviewer.ReviewerContent
import com.intellij.ui.content.ContentFactory
import com.intellij.ui.content.ContentManager
import javax.inject.Inject
import javax.inject.Named

class ToolWindowContentPresenter
    @Inject
    constructor(
            private val githubRepoFinder: FindGithubRepoForRootFolderInteractor,
            @Reviewee private val assignedPullRequestInteractor: PullRequestsByRepositoryInteractor,
            @Reviewer private val reviewerPullRequestInteractor: PullRequestsByRepositoryInteractor,
            private val contentFactory: ContentFactory,
            @Named("GH_Reviews") private val contentManager: ContentManager,
            private val revieweeView: RevieweeContent.View,
            private val revieweePresenter: RevieweeContent.Presenter,
            private val reviewerView: ReviewerContent.View,
            private val reviewerPresenter: ReviewerContent.Presenter)
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
            assignedPullRequestInteractor.run(it)?.let {
                revieweePresenter.presentPullRequests(it)
            }
            reviewerPullRequestInteractor.run(it)?.let {
                reviewerPresenter.presentPullRequests(it)
            }
        }
    }
}

