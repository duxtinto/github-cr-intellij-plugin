package com.duxtinto.intellij.plugin.github.codereviews.ide.acl.services.github.repositories

import com.duxtinto.intellij.plugin.github.codereviews.domain.repositories.GithubRepositoryEntity
import com.duxtinto.intellij.plugin.github.codereviews.domain.repositories.RepositoriesDomainContract
import com.duxtinto.intellij.plugin.github.codereviews.domain.repositories.RepositoriesDomainContract.Git
import com.duxtinto.intellij.plugin.github.codereviews.ide.acl.entities.git.GitRepositoryExt
import org.jetbrains.plugins.github.util.GithubUrlUtil
import org.jetbrains.plugins.github.util.GithubUtil
import javax.inject.Inject

class GithubRepositoryFinderImpl
    @Inject
    constructor()
    : RepositoriesDomainContract.GithubRepositoryFinder {

    override fun find(gitRepository: Git.Repository): GithubRepositoryEntity? {
        val remote = GithubUtil.findGithubRemote(gitRepository as GitRepositoryExt) ?: return null
        val path = GithubUrlUtil.getUserAndRepositoryFromRemoteUrl(remote.getSecond()) ?: return null
        return GithubRepositoryEntity(path.repository, path.user)
    }
}
