package com.duxtinto.intellij.plugin.github.codereviews.ide.acl.services;

import com.duxtinto.intellij.plugin.github.codereviews.domain.repositories.GithubRepositoryEntity;
import com.duxtinto.intellij.plugin.github.codereviews.domain.repositories.RepositoriesDomainContract;
import com.duxtinto.intellij.plugin.github.codereviews.ide.acl.entities.GitRepositoryExt;
import com.intellij.openapi.util.Pair;
import git4idea.repo.GitRemote;
import org.jetbrains.plugins.github.api.GithubFullPath;
import org.jetbrains.plugins.github.util.GithubUrlUtil;
import org.jetbrains.plugins.github.util.GithubUtil;

import javax.annotation.Nullable;
import javax.inject.Inject;

public class GithubRepositoryFinderImpl implements RepositoriesDomainContract.GithubRepositoryFinder {
    @Inject
    public GithubRepositoryFinderImpl() {
    }

    @Override
    @Nullable
    public GithubRepositoryEntity find(GitRepositoryExt repository) {
        Pair<GitRemote, String> remote = GithubUtil.findGithubRemote(repository.delegate());
        if (remote == null) {
            return null;
        }

        GithubFullPath path = GithubUrlUtil.getUserAndRepositoryFromRemoteUrl(remote.getSecond());
        if (path == null) {
            return null;
        }

        return new GithubRepositoryEntity(path.getRepository(), path.getUser());
    }
}
