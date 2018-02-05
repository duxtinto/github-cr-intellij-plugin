package com.duxtinto.intellij.plugin.github.codereviews.ide.acl.services

import com.duxtinto.intellij.plugin.github.codereviews.domain.repositories.RepositoriesDomainContract
import com.duxtinto.intellij.plugin.github.codereviews.ide.acl.entities.GitRepositoryExt
import com.duxtinto.intellij.plugin.github.codereviews.ide.acl.entities.ProjectExt
import org.jetbrains.plugins.github.util.GithubUtil
import javax.inject.Inject

class GitRepositoryFinderImpl
    @Inject
    constructor(private val project: ProjectExt)
    : RepositoriesDomainContract.GitRepositoryFinder {

    override fun findRootRepo(): GitRepositoryExt? {
        val repository = GithubUtil.getGitRepository(project, null)
        return if (repository != null) GitRepositoryExt(repository) else null
    }
}
