package com.duxtinto.intellij.plugin.github.codereviews.ide.acl.entities

import com.google.common.collect.ForwardingObject
import git4idea.repo.GitRemote

class GitRemoteExt(private val delegate: GitRemote) : ForwardingObject() {

    override fun delegate(): GitRemote {
        return delegate
    }
}
