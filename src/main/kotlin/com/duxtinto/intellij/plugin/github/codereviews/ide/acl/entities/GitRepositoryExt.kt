package com.duxtinto.intellij.plugin.github.codereviews.ide.acl.entities

import com.google.common.collect.ForwardingObject
import git4idea.repo.GitRepository
import org.jetbrains.plugins.github.util.GithubUtil
import java.util.stream.Collectors

class GitRepositoryExt private constructor(private val delegate: GitRepository) : ForwardingObject() {

    internal val remotes: Collection<GitRemoteExt>
        get() = delegate
                    .remotes
                    .map { GitRemoteExt(it) }

    public override fun delegate(): GitRepository {
        return delegate
    }

    fun hasGithubRemote(): Boolean {
        return GithubUtil.isRepositoryOnGitHub(this.delegate())
    }

    companion object {
        fun create(delegate: GitRepository): GitRepositoryExt {
            return GitRepositoryExt(delegate)
        }
    }
}
