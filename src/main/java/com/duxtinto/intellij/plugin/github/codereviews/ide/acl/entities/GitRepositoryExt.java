package com.duxtinto.intellij.plugin.github.codereviews.ide.acl.entities;

import com.google.common.collect.ForwardingObject;
import git4idea.repo.GitRepository;
import org.jetbrains.plugins.github.util.GithubUtil;

import javax.annotation.Nonnull;
import java.util.Collection;
import java.util.stream.Collectors;

public class GitRepositoryExt extends ForwardingObject {
    @Nonnull
    private final GitRepository delegate;

    private GitRepositoryExt(@Nonnull GitRepository delegate) {
        this.delegate = delegate;
    }

    public static GitRepositoryExt create(GitRepository delegate) {
        return new GitRepositoryExt(delegate);
    }

    @Override
    public GitRepository delegate() {
        return delegate;
    }

    public boolean hasGithubRemote() {
        return GithubUtil.isRepositoryOnGitHub(this.delegate());
    }

    @Nonnull
    Collection<GitRemoteExt> getRemotes() {
        return delegate.getRemotes().stream()
                .map(GitRemoteExt::new)
                .collect(Collectors.toList());
    }
}
