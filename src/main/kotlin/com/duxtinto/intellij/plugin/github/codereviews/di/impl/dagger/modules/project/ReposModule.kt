package com.duxtinto.intellij.plugin.github.codereviews.di.impl.dagger.modules.project

import com.duxtinto.intellij.plugin.github.codereviews.di.scopes.ProjectScoped
import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.ActionOnPullRequestInteractor
import com.duxtinto.intellij.plugin.github.codereviews.domain.repositories.RepositoriesDomainContract
import com.duxtinto.intellij.plugin.github.codereviews.domain.repositories.git.CheckoutBranchInteractor
import com.duxtinto.intellij.plugin.github.codereviews.domain.repositories.git.PullBranchInteractor
import com.duxtinto.intellij.plugin.github.codereviews.ide.acl.services.git.GitRepositoryFinderImpl
import com.duxtinto.intellij.plugin.github.codereviews.ide.acl.services.github.repositories.GithubRepositoryFinderImpl
import dagger.Binds
import dagger.Module
import javax.inject.Named

@Module
abstract class ReposModule {
    @Binds
    @ProjectScoped
    abstract fun provideGitRepositoryFinder(gitRepositoryFinder: GitRepositoryFinderImpl): RepositoriesDomainContract.Git.RepositoryFinder

    @Binds
    @ProjectScoped
    abstract fun provideGithubRepositoryFinder(githubRepositoryFinder: GithubRepositoryFinderImpl): RepositoriesDomainContract.GithubRepositoryFinder

    @Binds
    @ProjectScoped
    @Named("checkoutBranch")
    abstract fun provideCheckoutBranchInteractor(interactor: CheckoutBranchInteractor): ActionOnPullRequestInteractor

    @Binds
    @ProjectScoped
    @Named("pullBranch")
    abstract fun providePullBranchInteractor(interactor: PullBranchInteractor): ActionOnPullRequestInteractor
}
