package com.duxtinto.intellij.plugin.github.codereviews.di.impl.dagger.modules

import com.duxtinto.intellij.plugin.github.codereviews.di.scopes.ProjectScoped
import com.duxtinto.intellij.plugin.github.codereviews.domain.repositories.RepositoriesDomainContract
import com.duxtinto.intellij.plugin.github.codereviews.events.repos.GitChangeListener
import com.duxtinto.intellij.plugin.github.codereviews.ide.acl.services.GitRepositoryFinderImpl
import com.duxtinto.intellij.plugin.github.codereviews.ide.acl.services.GithubRepositoryFinderImpl
import com.intellij.dvcs.repo.VcsRepositoryMappingListener
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

    @Binds
    @ProjectScoped
    abstract fun provideVcsRepositoryMappingListener(changeListener: GitChangeListener): VcsRepositoryMappingListener
}
