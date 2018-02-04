package com.duxtinto.intellij.plugin.github.codereviews.ide.acl.services

import com.duxtinto.intellij.plugin.github.codereviews.domain.repositories.GithubRepositoryEntity
import com.duxtinto.intellij.plugin.github.codereviews.domain.repositories.RepositoriesDomainContract
import com.duxtinto.intellij.plugin.github.codereviews.ide.acl.entities.GitRepositoryExt
import org.jetbrains.plugins.github.util.GithubUrlUtil
import org.jetbrains.plugins.github.util.GithubUtil
import javax.inject.Inject

class GithubRepositoryFinderImpl @Inject
constructor() : RepositoriesDomainContract.GithubRepositoryFinder {

    override fun find(gitRepository: GitRepositoryExt): GithubRepositoryEntity? {
        val remote = GithubUtil.findGithubRemote(gitRepository.delegate()) ?: return null
        val path = GithubUrlUtil.getUserAndRepositoryFromRemoteUrl(remote.getSecond()) ?: return null

        return GithubRepositoryEntity(path.repository, path.user)
    }
}
