package com.duxtinto.intellij.plugin.github.codereviews.ide.acl.entities

import com.google.common.collect.ForwardingObject
import org.jetbrains.plugins.github.api.GithubConnection

class GithubConnectionExt private constructor(private val delegate: GithubConnection) : ForwardingObject() {

    public override fun delegate(): GithubConnection {
        return delegate
    }

    companion object {
        fun create(delegate: GithubConnection): GithubConnectionExt {
            return GithubConnectionExt(delegate)
        }
    }
}
