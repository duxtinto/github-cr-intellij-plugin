package com.duxtinto.intellij.plugin.github.codereviews.ide.acl.services.git

import com.duxtinto.intellij.plugin.github.codereviews.domain.repositories.RepositoriesDomainContract.Git
import com.duxtinto.intellij.plugin.github.codereviews.ide.acl.entities.git.GitRepositoryExt
import git4idea.branch.GitBrancher
import git4idea.repo.GitRepository

class IdeaBranchOperator(private val brancher: GitBrancher) :
        GitBrancher by brancher,
        Git.BranchOperator {
    override fun checkOut(ref: String, repository: Git.Repository) {
        if (repository is GitRepositoryExt && repository.currentBranch?.name != ref) {
            brancher.checkout(ref, false, mutableListOf(repository as GitRepository), null)
        }
    }
}
