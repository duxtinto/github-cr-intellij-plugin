package com.duxtinto.intellij.plugin.github.codereviews.ide.acl.entities

import git4idea.repo.GitRepository
import org.jetbrains.plugins.github.util.GithubUtil

class GitRepositoryExt (private val repository: GitRepository) : GitRepository by repository {

    internal val remotes: Collection<GitRemoteExt>
        get() = repository.remotes
                    .map { GitRemoteExt(it) }

    fun hasGithubRemote(): Boolean {
        return GithubUtil.isRepositoryOnGitHub(this)
    }
}
