package com.duxtinto.intellij.plugin.github.codereviews.di.impl.dagger.modules

import com.duxtinto.intellij.plugin.github.codereviews.di.scopes.ProjectScoped
import com.duxtinto.intellij.plugin.github.codereviews.domain.repositories.RepositoriesDomainContract
import com.duxtinto.intellij.plugin.github.codereviews.ide.acl.services.git.GitRepositoryFinderImpl
import com.duxtinto.intellij.plugin.github.codereviews.ide.acl.services.github.repositories.GithubRepositoryFinderImpl
import dagger.Binds
import dagger.Module

@Module
abstract class ReposModule {
    @Binds
    @ProjectScoped
    abstract fun provideGitRepositoryFinder(gitRepositoryFinder: GitRepositoryFinderImpl): RepositoriesDomainContract.GitRepositoryFinder

    @Binds
    @ProjectScoped
    abstract fun provideGithubRepositoryFinder(githubRepositoryFinder: GithubRepositoryFinderImpl): RepositoriesDomainContract.GithubRepositoryFinder
}
