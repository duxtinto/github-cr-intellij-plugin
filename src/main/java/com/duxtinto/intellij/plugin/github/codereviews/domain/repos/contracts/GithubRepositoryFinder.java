package com.duxtinto.intellij.plugin.github.codereviews.domain.repos.contracts;

import com.duxtinto.intellij.plugin.github.codereviews.domain.repos.GithubRepositoryEntity;
import com.duxtinto.intellij.plugin.github.codereviews.ide.acl.entities.GitRepositoryExt;

import javax.annotation.Nullable;

public interface GithubRepositoryFinder {
    @Nullable
    GithubRepositoryEntity find(GitRepositoryExt gitRepository);
}
