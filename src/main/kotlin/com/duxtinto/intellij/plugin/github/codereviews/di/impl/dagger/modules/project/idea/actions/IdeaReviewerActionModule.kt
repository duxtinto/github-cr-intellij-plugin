package com.duxtinto.intellij.plugin.github.codereviews.di.impl.dagger.modules.project.idea.actions

import com.duxtinto.intellij.plugin.github.codereviews.di.qualifiers.Reviewee
import com.duxtinto.intellij.plugin.github.codereviews.di.qualifiers.Reviewer
import com.duxtinto.intellij.plugin.github.codereviews.di.scopes.ProjectScoped
import com.duxtinto.intellij.plugin.github.codereviews.ide.acl.presentation.toolwindow.toolbar.RevieweeToolbarFactory
import com.duxtinto.intellij.plugin.github.codereviews.ide.acl.presentation.toolwindow.toolbar.reviewee.RefreshPullRequestListAction
import com.duxtinto.intellij.plugin.github.codereviews.presentation.Toolbar
import com.intellij.openapi.actionSystem.ActionManager
import com.intellij.openapi.actionSystem.AnAction
import dagger.Module
import dagger.Provides

@Module
class IdeaReviewerActionModule {
    @Provides
    @Reviewer
    @ProjectScoped
    fun provideRevieweeToolbarActions(
            refreshPullRequestsAction: RefreshPullRequestListAction
    ): List<AnAction> {
        return listOf(
                refreshPullRequestsAction
        )
    }

    @Provides
    @Reviewer
    @ProjectScoped
    fun provideToolbarFactory(
            actionManager: ActionManager,
            @Reviewee actions: @JvmSuppressWildcards List<AnAction>
    ): Toolbar.Factory {
        return RevieweeToolbarFactory(actionManager, actions)
    }

    @Provides
    @Reviewer
    @ProjectScoped
    fun provideToolbar(@Reviewee toolbar: Toolbar.Factory): Toolbar {
        return toolbar.create()
    }
}
