package com.duxtinto.intellij.plugin.github.codereviews.di.impl.dagger.modules.pullrequest

import com.duxtinto.intellij.plugin.github.codereviews.data.pullrequests.PullRequestRepository
import com.duxtinto.intellij.plugin.github.codereviews.di.scopes.ProjectScoped
import com.duxtinto.intellij.plugin.github.codereviews.domain.DomainDataMapper
import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.GetAllMyOpenForRepoInteractor
import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.PullRequestDomainContract
import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.PullRequestEntity
import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.PullRequestsByRepositoryInteractor
import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.reviews.CodeReviewsByPullRequestInteractor
import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.reviews.GetAllReviewsForInteractor
import com.duxtinto.intellij.plugin.github.codereviews.net.pullrequests.apiv3.PullRequestFetcher
import com.duxtinto.intellij.plugin.github.codereviews.net.pullrequests.apiv3.PullRequestResponse
import com.duxtinto.intellij.plugin.github.codereviews.net.pullrequests.apiv3.PullRequestResponseMapper
import com.duxtinto.intellij.plugin.github.codereviews.services.pullrequests.GithubDescriptionParser
import dagger.Binds
import dagger.Module

@Module(includes = [
    RevieweePullRequestModule::class
])
abstract class PullRequestModule {
    @Binds
    @ProjectScoped
    abstract fun provideGetAllMyOpenForRepoInteractor(interactor: GetAllMyOpenForRepoInteractor): PullRequestsByRepositoryInteractor

    @Binds
    @ProjectScoped
    abstract fun provideGetAllReviewsForInteractor(interactor: GetAllReviewsForInteractor): CodeReviewsByPullRequestInteractor

    @Binds
    abstract fun providePullRequestDomainContractFetcher(fetcher: PullRequestFetcher): PullRequestDomainContract.Fetcher

    @Binds
    abstract fun providePullRequestDomainContractRepository(repository: PullRequestRepository): PullRequestDomainContract.Repository

    @Binds
    abstract fun providePullRequestDomainContractDescriptionParser(parser: GithubDescriptionParser): PullRequestDomainContract.DescriptionParser

    @Binds
    @ProjectScoped
    abstract fun providePullRequestResponseMapper(mapper: PullRequestResponseMapper): DomainDataMapper<PullRequestEntity, PullRequestResponse>
}
