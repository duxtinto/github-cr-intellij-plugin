package com.duxtinto.intellij.plugin.github.codereviews.domain.repositories

import com.duxtinto.intellij.plugin.github.codereviews.ide.acl.entities.git.GitRepositoryExt

interface RepositoriesDomainContract {
    interface GithubRepositoryFinder {
        fun find(gitRepository: GitRepositoryExt): GithubRepositoryEntity?
    }

    interface GitRepositoryFinder {
        fun findRootRepo(): GitRepositoryExt?
    }
}
