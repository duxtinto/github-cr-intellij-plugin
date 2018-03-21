package com.duxtinto.intellij.plugin.github.codereviews.di.impl.dagger.modules.project

import com.duxtinto.intellij.plugin.github.codereviews.di.scopes.ProjectScoped
import com.duxtinto.intellij.plugin.github.codereviews.ide.acl.presentation.toolwindow.ToolWindowContent
import com.duxtinto.intellij.plugin.github.codereviews.ide.acl.presentation.toolwindow.ToolWindowContentPresenter
import com.duxtinto.intellij.plugin.github.codereviews.presentation.reviewee.RevieweeContent
import com.duxtinto.intellij.plugin.github.codereviews.presentation.reviewee.RevieweePresenter
import com.duxtinto.intellij.plugin.github.codereviews.presentation.reviewee.RevieweeView
import com.duxtinto.intellij.plugin.github.codereviews.presentation.reviewer.ReviewerContent
import com.duxtinto.intellij.plugin.github.codereviews.presentation.reviewer.ReviewerPresenter
import com.duxtinto.intellij.plugin.github.codereviews.presentation.reviewer.ReviewerView
import dagger.Binds
import dagger.Module

@Module
abstract class ToolWindowModule {
    @Binds
    @ProjectScoped
    abstract fun provideToolWindowContentPresenter(presenter: ToolWindowContentPresenter): ToolWindowContent.Presenter

    @Binds
    @ProjectScoped
    abstract fun provideToolWindowContentRevieweeView(view: RevieweeView): RevieweeContent.View

    @Binds
    @ProjectScoped
    abstract fun provideToolWindowContentRevieweePresenter(presenter: RevieweePresenter): RevieweeContent.Presenter

    @Binds
    @ProjectScoped
    abstract fun provideToolWindowContentReviewerView(view: ReviewerView): ReviewerContent.View

    @Binds
    @ProjectScoped
    abstract fun provideToolWindowContentReviewerPresenter(presenter: ReviewerPresenter): ReviewerContent.Presenter
}
