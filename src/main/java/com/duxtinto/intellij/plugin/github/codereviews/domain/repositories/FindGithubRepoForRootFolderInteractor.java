package com.duxtinto.intellij.plugin.github.codereviews.domain.repositories;

import com.duxtinto.intellij.plugin.github.codereviews.domain.Interactor;
import com.duxtinto.intellij.plugin.github.codereviews.ide.acl.entities.GitRepositoryExt;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.inject.Inject;

public class FindGithubRepoForRootFolderInteractor implements Interactor<Void, GithubRepositoryEntity> {
    @Nonnull
    private final RepositoriesDomainContract.GitRepositoryFinder gitRepositoryFinder;
    @Nonnull
    private final RepositoriesDomainContract.GithubRepositoryFinder githubRepositoryFinder;

    @Inject
    public FindGithubRepoForRootFolderInteractor(
            @Nonnull RepositoriesDomainContract.GitRepositoryFinder gitRepositoryFinder,
            @Nonnull RepositoriesDomainContract.GithubRepositoryFinder githubRepositoryFinder) {
        this.gitRepositoryFinder = gitRepositoryFinder;
        this.githubRepositoryFinder = githubRepositoryFinder;
    }

    @Override
    @Nullable
    public GithubRepositoryEntity run(Void request) {
        GitRepositoryExt rootGitRepo = gitRepositoryFinder.findRootRepo();
        if (rootGitRepo == null) {
            return null;
        }

        return githubRepositoryFinder.find(rootGitRepo);
    }
}
