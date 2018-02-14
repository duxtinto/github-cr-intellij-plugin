package com.duxtinto.intellij.plugin.github.codereviews.di.impl.dagger.modules.pullrequest

import com.duxtinto.intellij.plugin.github.codereviews.di.qualifiers.Reviewee
import com.duxtinto.intellij.plugin.github.codereviews.di.scopes.ProjectScoped
import com.duxtinto.intellij.plugin.github.codereviews.presentation.pullrequestlist.PullRequestList
import com.duxtinto.intellij.plugin.github.codereviews.presentation.reviewee.pullrequests.events.SelectionListener
import com.duxtinto.intellij.plugin.github.codereviews.presentation.reviewee.pullrequests.PullRequestListPresenter
import com.duxtinto.intellij.plugin.github.codereviews.presentation.reviewee.pullrequests.PullRequestListView
import dagger.Binds
import dagger.Module

@Module
abstract class RevieweePullRequestModule {
    @Binds
    @Reviewee
    @ProjectScoped
    abstract fun providePullRequestListView(view: PullRequestListView): PullRequestList.View

    @Binds
    @Reviewee
    @ProjectScoped
    abstract fun providePullRequestListPresenter(presenter: PullRequestListPresenter): PullRequestList.Presenter

    @Binds
    @Reviewee
    @ProjectScoped
    abstract fun providePullRequestListViewEventsSelectionListener(listener: SelectionListener): PullRequestList.View.Events.SelectionListener
}
