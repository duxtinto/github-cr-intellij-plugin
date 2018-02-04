package com.duxtinto.intellij.plugin.github.codereviews.di.impl.dagger.modules

import com.duxtinto.intellij.plugin.github.codereviews.di.scopes.ProjectScoped
import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.reviews.comments.ActionOnReviewCommentInteractor
import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.reviews.comments.CommentsByReviewInteractor
import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.reviews.comments.GetAllCommentsForInteractor
import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.reviews.comments.GoToCommentLineInteractor
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
}