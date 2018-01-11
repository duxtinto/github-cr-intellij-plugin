package com.duxtinto.intellij.plugin.github.codereviews.ide.acl.entities;

import com.google.common.collect.ForwardingObject;
import git4idea.repo.GitRemote;

public class GitRemoteExt extends ForwardingObject{
    private final GitRemote delegate;

    public GitRemoteExt(GitRemote remote) {
        this.delegate = remote;
    }

    @Override
    protected GitRemote delegate() {
        return delegate;
    }
}
