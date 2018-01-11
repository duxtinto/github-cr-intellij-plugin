package com.duxtinto.intellij.plugin.github.codereviews.ide.acl.entities;

import com.google.common.base.Enums;
import com.google.common.collect.ForwardingObject;
import org.jetbrains.plugins.github.api.data.GithubPullRequest;

import javax.annotation.Nonnull;

public class GithubPullRequestExt extends ForwardingObject {
    private final GithubPullRequest delegate;

    private GithubPullRequestExt(@Nonnull GithubPullRequest delegate) {
        this.delegate = delegate;
    }

    public static GithubPullRequestExt create(@Nonnull GithubPullRequest  delegate) {
        return new GithubPullRequestExt(delegate);
    }

    @Override
    protected GithubPullRequest delegate() {
        return delegate;
    }

    public long getNumber() {
        return delegate.getNumber();
    }

    @Nonnull
    public State getState() {
        return State.fromString(delegate.getState());
    }

    @Nonnull
    public String getTitle() {
        return delegate.getTitle();
    }
    public enum State {
        UNKNOWN,
        OPEN,
        CLOSED;

        public static State fromString(String string) {
            return Enums.getIfPresent(State.class, string.toUpperCase()).or(State.UNKNOWN);
        }
    }
}
