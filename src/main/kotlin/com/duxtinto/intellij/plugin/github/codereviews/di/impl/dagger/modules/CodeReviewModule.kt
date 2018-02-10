package com.duxtinto.intellij.plugin.github.codereviews.di.impl.dagger.modules

import com.duxtinto.intellij.plugin.github.codereviews.data.codereviews.CodeReviewsDataContract
import com.duxtinto.intellij.plugin.github.codereviews.data.codereviews.CodeReviewsRepository
import com.duxtinto.intellij.plugin.github.codereviews.data.codereviews.comments.CommentsRepository
import com.duxtinto.intellij.plugin.github.codereviews.di.scopes.ProjectScoped
import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.reviews.CodeReviewsDomainContract
import com.duxtinto.intellij.plugin.github.codereviews.net.codereviews.apiv3.CodeReviewsFetcher
import com.duxtinto.intellij.plugin.github.codereviews.presentation.toolwindow.codereviews.CodeReviews
import com.duxtinto.intellij.plugin.github.codereviews.presentation.toolwindow.codereviews.CodeReviewsPresenter
import com.duxtinto.intellij.plugin.github.codereviews.presentation.toolwindow.codereviews.CodeReviewsTreeCellRenderer
import com.duxtinto.intellij.plugin.github.codereviews.presentation.toolwindow.codereviews.CodeReviewsView
import com.duxtinto.intellij.plugin.github.codereviews.presentation.toolwindow.codereviews.events.MouseListener
import dagger.Binds
import dagger.Module
import javax.inject.Named
import javax.swing.tree.TreeCellRenderer

@Module(includes = [
    ReviewCommentModule::class
])
abstract class CodeReviewModule {
    @Binds
    @ProjectScoped
    abstract fun provideCodeReviewsDomainContractRepository(repository: CodeReviewsRepository): CodeReviewsDomainContract.Repository

    @Binds
    @ProjectScoped
    abstract fun provideCodeReviewCommentsDomainContractRepository(repository: CommentsRepository): CodeReviewsDomainContract.Comments.Repository

    @Binds
    @ProjectScoped
    abstract fun provideCodeReviewsDataContractFetcher(fetcher: CodeReviewsFetcher): CodeReviewsDataContract.Fetcher

    @Binds
    @ProjectScoped
    abstract fun provideCodeReviewsView(view: CodeReviewsView): CodeReviews.View

    @Binds
    @ProjectScoped
    @Named("code_reviews")
    abstract fun provideCodeReviewsTreeCellRenderer(cellRenderer: CodeReviewsTreeCellRenderer): TreeCellRenderer

    @Binds
    @ProjectScoped
    abstract fun provideCodeReviewsViewEventsMouseListener(listener: MouseListener): CodeReviews.View.Events.MouseListener

    @Binds
    @ProjectScoped
    abstract fun provideCodeReviewsPresenter(presenter: CodeReviewsPresenter): CodeReviews.Presenter
}
