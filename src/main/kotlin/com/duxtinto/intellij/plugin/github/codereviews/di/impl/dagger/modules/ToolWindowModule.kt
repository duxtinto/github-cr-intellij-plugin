package com.duxtinto.intellij.plugin.github.codereviews.di.impl.dagger.modules

import com.duxtinto.intellij.plugin.github.codereviews.di.scopes.ProjectScoped
import com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow.ToolWindowContent
import com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow.ToolWindowContentPresenter
import com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow.reviewee.RevieweeView
import com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow.reviewer.ReviewerView
import dagger.Binds
import dagger.Module

@Module
abstract class ToolWindowModule {
    @Binds
    @ProjectScoped
    abstract fun provideToolWindowContentPresenter(presenter: ToolWindowContentPresenter): ToolWindowContent.Presenter

    @Binds
    @ProjectScoped
    abstract fun provideToolWindowContentRevieweeView(revieweeView: RevieweeView): ToolWindowContent.RevieweeView

    @Binds
    @ProjectScoped
    abstract fun provideToolWindowContentReviewerView(reviewerView: ReviewerView): ToolWindowContent.ReviewerView
}
