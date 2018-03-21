package com.duxtinto.intellij.plugin.github.codereviews.di.impl.dagger.modules.project.pullrequest

import com.duxtinto.intellij.plugin.github.codereviews.di.qualifiers.Reviewer
import com.duxtinto.intellij.plugin.github.codereviews.di.scopes.ProjectScoped
import com.duxtinto.intellij.plugin.github.codereviews.presentation.pullrequestlist.PullRequestList
import com.duxtinto.intellij.plugin.github.codereviews.presentation.pullrequestlist.columns.ColumnInfoFactory
import com.duxtinto.intellij.plugin.github.codereviews.presentation.pullrequestlist.columns.ReviewerColumnInfoFactory
import com.duxtinto.intellij.plugin.github.codereviews.presentation.reviewer.pullrequests.events.MouseListener
import com.duxtinto.intellij.plugin.github.codereviews.presentation.reviewer.pullrequests.PullRequestListPresenter
import com.duxtinto.intellij.plugin.github.codereviews.presentation.reviewer.pullrequests.PullRequestListView
import com.duxtinto.intellij.plugin.github.codereviews.presentation.reviewer.pullrequests.events.SelectionListener
import dagger.Binds
import dagger.Module

@Module
abstract class ReviewerModule {
    @Binds
    @Reviewer
    @ProjectScoped
    abstract fun provideColumnFactory(factory: ReviewerColumnInfoFactory): ColumnInfoFactory

    @Binds
    @Reviewer
    @ProjectScoped
    abstract fun providePullRequestListView(view: PullRequestListView): PullRequestList.View

    @Binds
    @Reviewer
    @ProjectScoped
    abstract fun providePullRequestListPresenter(presenter: PullRequestListPresenter): PullRequestList.Presenter

    @Binds
    @Reviewer
    @ProjectScoped
    abstract fun providePullRequestListViewEventsSelectionListener(listener: SelectionListener): PullRequestList.View.Events.SelectionListener

    @Binds
    @Reviewer
    @ProjectScoped
    abstract fun providePullRequestListViewEventsMouseListener(listener: MouseListener): PullRequestList.View.Events.MouseListener
}
