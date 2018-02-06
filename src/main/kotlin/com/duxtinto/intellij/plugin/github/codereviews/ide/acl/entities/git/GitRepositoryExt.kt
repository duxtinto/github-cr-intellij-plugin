package com.duxtinto.intellij.plugin.github.codereviews.ide.acl.entities.git

import git4idea.repo.GitRepository
import org.jetbrains.plugins.github.util.GithubUtil

class GitRepositoryExt (private val ideaRepository: GitRepository) : GitRepository by ideaRepository {

    internal val remotes: Collection<GitRemoteExt>
        get() = ideaRepository.remotes
                    .map { GitRemoteExt(it) }

    fun hasGithubRemote(): Boolean {
        return GithubUtil.isRepositoryOnGitHub(this)
    }
}
