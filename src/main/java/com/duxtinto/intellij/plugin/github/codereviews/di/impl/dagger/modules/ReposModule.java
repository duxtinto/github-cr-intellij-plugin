package com.duxtinto.intellij.plugin.github.codereviews.di.impl.dagger.modules;

import com.duxtinto.intellij.plugin.github.codereviews.di.scopes.ProjectScoped;
import com.duxtinto.intellij.plugin.github.codereviews.domain.repositories.RepositoriesDomainContract;
import com.duxtinto.intellij.plugin.github.codereviews.ide.acl.services.GitRepositoryFinderImpl;
import com.duxtinto.intellij.plugin.github.codereviews.ide.acl.services.GithubRepositoryFinderImpl;
import dagger.Binds;
import dagger.Module;

@Module
public abstract class ReposModule {
    @Binds
    @ProjectScoped
    public abstract RepositoriesDomainContract.GitRepositoryFinder provideGitRepositoryFinder(GitRepositoryFinderImpl gitRepositoryFinder);

    @Binds
    @ProjectScoped
    public abstract RepositoriesDomainContract.GithubRepositoryFinder provideGithubRepositoryFinder(GithubRepositoryFinderImpl githubRepositoryFinder);
}
