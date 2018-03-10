package com.duxtinto.intellij.plugin.github.codereviews.di.impl.dagger.modules.idea

import com.duxtinto.intellij.plugin.github.codereviews.di.qualifiers.Reviewee
import com.duxtinto.intellij.plugin.github.codereviews.di.qualifiers.Reviewer
import com.duxtinto.intellij.plugin.github.codereviews.di.scopes.ProjectScoped
import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.PullRequestsByRepositoryInteractor
import com.duxtinto.intellij.plugin.github.codereviews.domain.repositories.FindGithubRepoForRootFolderInteractor
import com.duxtinto.intellij.plugin.github.codereviews.domain.repositories.RepositoriesDomainContract.Git
import com.duxtinto.intellij.plugin.github.codereviews.ide.acl.entities.ProjectExt
import com.duxtinto.intellij.plugin.github.codereviews.ide.acl.events.repositories.GitChangeListener
import com.duxtinto.intellij.plugin.github.codereviews.ide.acl.services.git.IdeaBranchOperator
import com.duxtinto.intellij.plugin.github.codereviews.ide.acl.services.git.IdeaFetcher
import com.duxtinto.intellij.plugin.github.codereviews.presentation.reviewee.RevieweeContent
import com.duxtinto.intellij.plugin.github.codereviews.presentation.reviewer.ReviewerContent
import com.intellij.dvcs.repo.VcsRepositoryMappingListener
import com.intellij.openapi.progress.ProgressIndicator
import dagger.Module
import dagger.Provides
import git4idea.branch.GitBrancher
import git4idea.repo.GitRepositoryManager
import git4idea.update.GitFetcher

@Module
class IdeaGitModule {
    @Provides
    @ProjectScoped
    fun provideVcsRepositoryMappingListener(
            repoFinder: FindGithubRepoForRootFolderInteractor,
            @Reviewee assignedPrFetcher: PullRequestsByRepositoryInteractor,
            @Reviewer requestedPrFetcher: PullRequestsByRepositoryInteractor,
            revieweePresenter: RevieweeContent.Presenter,
            reviewerPresenter: ReviewerContent.Presenter)
            : VcsRepositoryMappingListener {
        return GitChangeListener(repoFinder, assignedPrFetcher, requestedPrFetcher, revieweePresenter, reviewerPresenter)
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

    @Provides
    @ProjectScoped
    fun provideGitFetcher(project: ProjectExt, progressInidcator: ProgressIndicator): Git.Fetcher {
        return IdeaFetcher(GitFetcher(project, progressInidcator, true))
    }
}