package com.duxtinto.intellij.plugin.github.codereviews.di.impl.dagger.modules.idea

import com.duxtinto.intellij.plugin.github.codereviews.di.qualifiers.Reviewee
import com.duxtinto.intellij.plugin.github.codereviews.di.scopes.ProjectScoped
import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.GetAllMyOpenForRepoInteractor
import com.duxtinto.intellij.plugin.github.codereviews.domain.repositories.FindGithubRepoForRootFolderInteractor
import com.duxtinto.intellij.plugin.github.codereviews.domain.repositories.RepositoriesDomainContract.Git
import com.duxtinto.intellij.plugin.github.codereviews.ide.acl.entities.ProjectExt
import com.duxtinto.intellij.plugin.github.codereviews.ide.acl.events.repositories.GitChangeListener
import com.duxtinto.intellij.plugin.github.codereviews.ide.acl.services.git.IdeaBranchOperator
import com.duxtinto.intellij.plugin.github.codereviews.presentation.pullrequestlist.PullRequestList
import com.intellij.dvcs.repo.VcsRepositoryMappingListener
import dagger.Module
import dagger.Provides
import git4idea.branch.GitBrancher
import git4idea.repo.GitRepositoryManager

@Module
class IdeaGitModule {
    @Provides
    @ProjectScoped
    fun provideVcsRepositoryMappingListener(
            repoFinder: FindGithubRepoForRootFolderInteractor,
            prFetcher: GetAllMyOpenForRepoInteractor,
            @Reviewee prPresenter: PullRequestList.Presenter): VcsRepositoryMappingListener {
        return GitChangeListener(repoFinder, prFetcher, prPresenter)
    }

    @Provides
    @ProjectScoped
    fun provideGitRepositoryManager(project: ProjectExt): GitRepositoryManager {
        return GitRepositoryManager.getInstance(project)
    }

    @Provides
    @ProjectScoped
    fun provideGitBrancher(project: ProjectExt): GitBrancher {
        return GitBrancher.getInstance(project)
    }

    @Provides
    @ProjectScoped
    fun provideBranchOperator(brancher: GitBrancher): Git.BranchOperator {
        return IdeaBranchOperator(brancher)
    }
}