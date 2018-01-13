package com.duxtinto.intellij.plugin.github.codereviews.ide.acl.services;

import com.duxtinto.intellij.plugin.github.codereviews.domain.repositories.RepositoriesDomainContract;
import com.duxtinto.intellij.plugin.github.codereviews.ide.acl.entities.GitRepositoryExt;
import com.duxtinto.intellij.plugin.github.codereviews.ide.acl.entities.ProjectExt;
import git4idea.repo.GitRepository;
import org.jetbrains.plugins.github.util.GithubUtil;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.inject.Inject;

public class GitRepositoryFinderImpl implements RepositoriesDomainContract.GitRepositoryFinder {
    @Nonnull
    private final ProjectExt project;

    @Inject
    public GitRepositoryFinderImpl(@Nonnull ProjectExt project) {
        this.project = project;
    }

    @Nullable
    public GitRepositoryExt findRootRepo() {
        GitRepository repository = GithubUtil.getGitRepository(project.delegate(), null);
        return (repository != null) ? GitRepositoryExt.create(repository) : null;
    }
}
