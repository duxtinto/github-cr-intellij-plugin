package com.duxtinto.intellij.plugin.github.codereviews.ide.acl.entities;

import com.google.common.collect.ForwardingObject;
import org.jetbrains.plugins.github.api.GithubConnection;

public class GithubConnectionExt extends ForwardingObject {
    private final GithubConnection delegate;

    private GithubConnectionExt(GithubConnection connection) {
        this.delegate = connection;
    }

    public static GithubConnectionExt create(GithubConnection delegate) {
        return new GithubConnectionExt(delegate);
    }

    @Override
    public GithubConnection delegate() {
        return delegate;
    }
}
