package com.duxtinto.intellij.plugin.github.codereviews.di.impl.dagger.modules;

import com.duxtinto.intellij.plugin.github.codereviews.di.scopes.ProjectScoped;
import com.duxtinto.intellij.plugin.github.codereviews.domain.repos.contracts.GitRepositoryFinder;
import com.duxtinto.intellij.plugin.github.codereviews.domain.repos.contracts.GithubRepositoryFinder;
import com.duxtinto.intellij.plugin.github.codereviews.domain.repos.interactors.FindGithubRepoForRootFolderInteractor;
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
            @Nonnull GitRepositoryFinder gitRepositoryFinder,
            @Nonnull GithubRepositoryFinder githubRepositoryFinder) {
        return new FindGithubRepoForRootFolderInteractor(gitRepositoryFinder, githubRepositoryFinder);
    }

    @Provides
    @ProjectScoped
    public GitRepositoryFinder provideGitRepositoryFinder(ProjectExt project) {
        return new GitRepositoryFinderImpl(project);
    }

    @Provides
    @ProjectScoped
    public GithubRepositoryFinder provideGithubRepositoryFinder() {
        return new GithubRepositoryFinderImpl();
    }
}
