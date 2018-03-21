package com.duxtinto.intellij.plugin.github.codereviews.di.impl.dagger.modules.project.pullrequest

import com.duxtinto.intellij.plugin.github.codereviews.data.pullrequests.PullRequestRepository
import com.duxtinto.intellij.plugin.github.codereviews.di.qualifiers.Reviewee
import com.duxtinto.intellij.plugin.github.codereviews.di.qualifiers.Reviewer
import com.duxtinto.intellij.plugin.github.codereviews.di.scopes.ProjectScoped
import com.duxtinto.intellij.plugin.github.codereviews.domain.DomainDataMapper
import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.*
import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.reviews.CodeReviewsByPullRequestInteractor
import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.reviews.GetAllReviewsForInteractor
import com.duxtinto.intellij.plugin.github.codereviews.net.pullrequests.apiv3.PullRequestFetcher
import com.duxtinto.intellij.plugin.github.codereviews.net.pullrequests.apiv3.PullRequestResponse
import com.duxtinto.intellij.plugin.github.codereviews.net.pullrequests.apiv3.PullRequestResponseMapper
import com.duxtinto.intellij.plugin.github.codereviews.presentation.pullrequestlist.columns.ColumnInfoFactory
import com.duxtinto.intellij.plugin.github.codereviews.services.pullrequests.GithubDescriptionParser
import com.intellij.util.ui.ColumnInfo
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module(includes = [
    RevieweeModule::class,
    ReviewerModule::class
])
abstract class PullRequestModule {
    @Module
    companion object {
        @Provides
        @ProjectScoped
        @Reviewee
        @JvmStatic
        fun provideRevieweePullRequestColumnInfoDefaultArray(@Reviewee columnInfoFactory: ColumnInfoFactory): Array<ColumnInfo<PullRequestEntity, *>> {
            return columnInfoFactory.createDefaultColumns()
        }

        @Provides
        @ProjectScoped
        @Reviewer
        @JvmStatic
        fun provideReviewerPullRequestColumnInfoDefaultArray(@Reviewer columnInfoFactory: ColumnInfoFactory): Array<ColumnInfo<PullRequestEntity, *>> {
            return columnInfoFactory.createDefaultColumns()
        }
    }

    @Binds
    @Reviewee
    @ProjectScoped
    abstract fun provideGetAllMyAssignedForRepoInteractor(interactor: GetAllMyAssignedForRepoInteractor): PullRequestsByRepositoryInteractor

    @Binds
    @Reviewer
    @ProjectScoped
    abstract fun provideGetAllMyRequestedForRepoInteractor(interactor: GetAllMyRequestedForRepoInteractor): PullRequestsByRepositoryInteractor

    @Binds
    @ProjectScoped
    abstract fun provideGetAllReviewsForInteractor(interactor: GetAllReviewsForInteractor): CodeReviewsByPullRequestInteractor

    @Binds
    @ProjectScoped
    abstract fun providePullRequestDomainContractFetcher(fetcher: PullRequestFetcher): PullRequestDomainContract.Fetcher

    @Binds
    @ProjectScoped
    abstract fun providePullRequestDomainContractRepository(repository: PullRequestRepository): PullRequestDomainContract.Repository

    @Binds
    @ProjectScoped
    abstract fun providePullRequestDomainContractDescriptionParser(parser: GithubDescriptionParser): PullRequestDomainContract.DescriptionParser

    @Binds
    @ProjectScoped
    abstract fun providePullRequestResponseMapper(mapper: PullRequestResponseMapper): DomainDataMapper<PullRequestEntity, PullRequestResponse>
}
