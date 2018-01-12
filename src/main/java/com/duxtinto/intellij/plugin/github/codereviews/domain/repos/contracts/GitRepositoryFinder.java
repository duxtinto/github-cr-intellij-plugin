package com.duxtinto.intellij.plugin.github.codereviews.domain.repos.contracts;

import com.duxtinto.intellij.plugin.github.codereviews.ide.acl.entities.GitRepositoryExt;

import javax.annotation.Nullable;

public interface GitRepositoryFinder {
    @Nullable
    GitRepositoryExt findRootRepo();
}
