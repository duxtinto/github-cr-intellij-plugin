package com.duxtinto.intellij.plugin.github.codereviews.di.impl.dagger.modules;

import com.duxtinto.intellij.plugin.github.codereviews.di.scopes.ProjectScoped;
import com.duxtinto.intellij.plugin.github.codereviews.domain.repositories.RepositoriesDomainContract;
import com.duxtinto.intellij.plugin.github.codereviews.domain.repositories.FindGithubRepoForRootFolderInteractor;
import com.duxtinto.intellij.plugin.github.codereviews.ide.acl.entities.ProjectExt;
import com.duxtinto.intellij.plugin.github.codereviews.ide.acl.services.GitRepositoryFinderImpl;
import com.duxtinto.intellij.plugin.github.codereviews.ide.acl.services.GithubRepositoryFinderImpl;
import dagger.Module;
import dagger.Provides;

import javax.annotation.Nonnull;

@Module
public class ReposModule {
    @Provides
    @ProjectScoped
    public FindGithubRepoForRootFolderInteractor provideFindGithubRepoForRootFolderInteractor(
            @Nonnull RepositoriesDomainContract.GitRepositoryFinder gitRepositoryFinder,
            @Nonnull RepositoriesDomainContract.GithubRepositoryFinder githubRepositoryFinder) {
        return new FindGithubRepoForRootFolderInteractor(gitRepositoryFinder, githubRepositoryFinder);
    }

    @Provides
    @ProjectScoped
    public RepositoriesDomainContract.GitRepositoryFinder provideGitRepositoryFinder(ProjectExt project) {
        return new GitRepositoryFinderImpl(project);
    }

    @Provides
    @ProjectScoped
    public RepositoriesDomainContract.GithubRepositoryFinder provideGithubRepositoryFinder() {
        return new GithubRepositoryFinderImpl();
    }
}
