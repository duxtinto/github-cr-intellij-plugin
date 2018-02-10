package com.duxtinto.intellij.plugin.github.codereviews.ide.acl.entities.git

import com.duxtinto.intellij.plugin.github.codereviews.domain.repositories.RepositoriesDomainContract
import git4idea.repo.GitRepository
import org.jetbrains.plugins.github.util.GithubUtil

class GitRepositoryExt (private val ideaRepository: GitRepository)
    : RepositoriesDomainContract.Git.Repository,
        GitRepository by ideaRepository {
    internal val remotes: Collection<GitRemoteExt>
        get() = ideaRepository.remotes
                    .map { GitRemoteExt(it) }

    override fun hasGithubRemote(): Boolean {
        return GithubUtil.isRepositoryOnGitHub(this)
    }
}
