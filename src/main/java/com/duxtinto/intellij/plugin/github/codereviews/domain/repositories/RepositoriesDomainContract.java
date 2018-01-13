package com.duxtinto.intellij.plugin.github.codereviews.domain.repositories;

import com.duxtinto.intellij.plugin.github.codereviews.ide.acl.entities.GitRepositoryExt;

import javax.annotation.Nullable;

public interface RepositoriesDomainContract {
    interface GithubRepositoryFinder {
        @Nullable
        GithubRepositoryEntity find(GitRepositoryExt gitRepository);
    }

    interface GitRepositoryFinder {
        @Nullable
        GitRepositoryExt findRootRepo();
    }
}
