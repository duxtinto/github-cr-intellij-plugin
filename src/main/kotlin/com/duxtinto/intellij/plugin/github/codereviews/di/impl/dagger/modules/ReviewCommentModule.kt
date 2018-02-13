package com.duxtinto.intellij.plugin.github.codereviews.di.impl.dagger.modules

import com.duxtinto.intellij.plugin.github.codereviews.di.scopes.ProjectScoped
import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.reviews.comments.*
import com.duxtinto.intellij.plugin.github.codereviews.presentation.Formatter
import com.duxtinto.intellij.plugin.github.codereviews.presentation.codereviews.formatters.CommentFormatter
import dagger.Binds
import dagger.Module
import javax.inject.Named

@Module
abstract class ReviewCommentModule {
    @Binds
    @ProjectScoped
    abstract fun provideCommentsByReviewInteractor(interactor: GetAllCommentsForInteractor): CommentsByReviewInteractor

    @Binds
    @ProjectScoped
    @Named("goToLine")
    abstract fun provideGoToCommentLineInteractor(interactor: GoToCommentLineInteractor): ActionOnReviewCommentInteractor

    @Binds
    abstract fun provideCommentFormatter(formatter: CommentFormatter): Formatter<CodeReviewCommentEntity, String>
}