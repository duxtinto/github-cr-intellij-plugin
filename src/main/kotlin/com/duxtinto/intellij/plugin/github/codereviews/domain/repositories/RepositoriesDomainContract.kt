package com.duxtinto.intellij.plugin.github.codereviews.domain.repositories

interface RepositoriesDomainContract {
    interface GithubRepositoryFinder {
        fun find(gitRepository: Git.Repository): GithubRepositoryEntity?
    }

    interface Git {
        interface Repository {
            fun hasGithubRemote() : Boolean
        }

        interface RepositoryFinder {
            fun findRootRepo(): Repository?
        }

        interface BranchOperator {
            fun checkOut(ref: String, repository: Git.Repository)
        }
    }
}
