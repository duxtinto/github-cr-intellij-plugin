package com.duxtinto.intellij.plugin.github.codereviews.di.impl.dagger.modules

import com.duxtinto.intellij.plugin.github.codereviews.di.scopes.ProjectScoped
import com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow.ToolWindowContent
import com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow.ToolWindowContentPresenter
import com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow.ToolWindowContentView
import com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow.codereviews.CodeReviews
import com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow.pullrequestlist.PullRequestList
import com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow.pullrequestlist.columns.ColumnInfoFactory
import com.intellij.ui.content.ContentFactory
import com.intellij.ui.content.ContentManager
import com.intellij.util.ui.ColumnInfo
import dagger.Module
import dagger.Provides

import javax.inject.Named

@Module
class ToolWindowModule {
    @Provides
    @ProjectScoped
    fun provideToolWindowContentPresenter(
            contentFactory: ContentFactory,
            @Named("GH_Reviews") contentManager: ContentManager): ToolWindowContent.Presenter {
        return ToolWindowContentPresenter(contentFactory, contentManager)
    }

    @Provides
    @ProjectScoped
    fun provideToolWindowContentView(
            pullRequestListView: PullRequestList.View,
            codeReviewsView: CodeReviews.View
    ): ToolWindowContent.View {
        return ToolWindowContentView(pullRequestListView, codeReviewsView)
    }

    @Provides
    @ProjectScoped
    @Named("default")
    fun provideColumnInfoDefaultArray(columnInfoFactory: ColumnInfoFactory): Array<ColumnInfo<*, *>> {
        return columnInfoFactory.createDefaultColumns()
    }
}
