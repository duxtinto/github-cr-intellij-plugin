package com.duxtinto.intellij.plugin.github.codereviews.domain.repos.interactors;

import com.duxtinto.intellij.plugin.github.codereviews.domain.Interactor;
import com.duxtinto.intellij.plugin.github.codereviews.domain.repos.GithubRepositoryEntity;
import com.duxtinto.intellij.plugin.github.codereviews.domain.repos.contracts.GitRepositoryFinder;
import com.duxtinto.intellij.plugin.github.codereviews.domain.repos.contracts.GithubRepositoryFinder;
import com.duxtinto.intellij.plugin.github.codereviews.ide.acl.entities.GitRepositoryExt;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class FindGithubRepoForRootFolderInteractor implements Interactor<Void, GithubRepositoryEntity> {
    @Nonnull
    private final GitRepositoryFinder gitRepositoryFinder;
    @Nonnull
    private final GithubRepositoryFinder githubRepositoryFinder;

    public FindGithubRepoForRootFolderInteractor(
            @Nonnull GitRepositoryFinder gitRepositoryFinder,
            @Nonnull GithubRepositoryFinder githubRepositoryFinder) {
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
