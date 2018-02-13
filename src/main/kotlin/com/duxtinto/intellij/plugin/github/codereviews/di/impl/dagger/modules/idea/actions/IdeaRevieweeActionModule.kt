package com.duxtinto.intellij.plugin.github.codereviews.di.impl.dagger.modules.idea.actions

import com.duxtinto.intellij.plugin.github.codereviews.di.qualifiers.Reviewee
import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.GetAllMyOpenForRepoInteractor
import com.duxtinto.intellij.plugin.github.codereviews.domain.repositories.FindGithubRepoForRootFolderInteractor
import com.duxtinto.intellij.plugin.github.codereviews.ide.acl.presentation.toolwindow.toolbar.RevieweeToolbarFactory
import com.duxtinto.intellij.plugin.github.codereviews.ide.acl.presentation.toolwindow.toolbar.reviewee.RefreshPullRequestListAction
import com.duxtinto.intellij.plugin.github.codereviews.presentation.Toolbar
import com.duxtinto.intellij.plugin.github.codereviews.presentation.reviewee.RevieweeContent
import com.intellij.openapi.actionSystem.ActionManager
import com.intellij.openapi.actionSystem.AnAction
import dagger.Module
import dagger.Provides

@Module
class IdeaRevieweeActionModule {
    @Provides
    fun provideRefreshPullRequestListAction(
            findRepoInteractor: FindGithubRepoForRootFolderInteractor,
            getPullRequestsInteractor: GetAllMyOpenForRepoInteractor,
            presenter: RevieweeContent.Presenter
    ): RefreshPullRequestListAction {
        return RefreshPullRequestListAction(findRepoInteractor, getPullRequestsInteractor, presenter)
    }

    @Provides
    @Reviewee
    fun provideRevieweeToolbarActions(
            refreshPullRequestsAction: RefreshPullRequestListAction
    ): List<AnAction> {
        return listOf(
                refreshPullRequestsAction
        )
    }

    @Provides
    @Reviewee
    fun provideToolbarFactory(
            actionManager: ActionManager,
            @Reviewee actions: @JvmSuppressWildcards List<AnAction>
    ): Toolbar.Factory {
        return RevieweeToolbarFactory(actionManager, actions)
    }

    @Provides
    @Reviewee
    fun provideToolbar(@Reviewee toolbar: Toolbar.Factory): Toolbar {
        return toolbar.create()
    }
}
