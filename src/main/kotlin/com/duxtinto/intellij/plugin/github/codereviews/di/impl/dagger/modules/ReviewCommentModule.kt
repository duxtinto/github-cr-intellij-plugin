package com.duxtinto.intellij.plugin.github.codereviews.di.impl.dagger.modules

import com.duxtinto.intellij.plugin.github.codereviews.di.scopes.ProjectScoped
import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.reviews.comments.CommentsByReviewInteractor
import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.reviews.comments.GetAllCommentsForInteractor
import dagger.Binds
import dagger.Module

@Module
abstract class ReviewCommentModule {
    @Binds
    @ProjectScoped
    abstract fun provideCommentsByReviewInteractor(review: GetAllCommentsForInteractor): CommentsByReviewInteractor
}