package com.duxtinto.intellij.plugin.github.codereviews.di.impl.dagger.modules.idea

import com.duxtinto.intellij.plugin.github.codereviews.di.scopes.ProjectScoped
import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.GetAllOpenForRepoInteractor
import com.duxtinto.intellij.plugin.github.codereviews.domain.repositories.FindGithubRepoForRootFolderInteractor
import com.duxtinto.intellij.plugin.github.codereviews.ide.acl.entities.ProjectExt
import com.duxtinto.intellij.plugin.github.codereviews.ide.acl.events.repositories.GitChangeListener
import com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow.pullrequestlist.PullRequestList
import com.intellij.dvcs.repo.VcsRepositoryMappingListener
import dagger.Module
import dagger.Provides
import git4idea.repo.GitRepositoryManager

@Module
class IdeaGitModule {
    @Provides
    @ProjectScoped
    fun provideVcsRepositoryMappingListener(
            repoFinder: FindGithubRepoForRootFolderInteractor,
            prFetcher: GetAllOpenForRepoInteractor,
            prPresenter: PullRequestList.Presenter): VcsRepositoryMappingListener {
        return GitChangeListener(repoFinder, prFetcher, prPresenter)
    }

    @Provides
    @ProjectScoped
    fun provideGitRepositoryManager(project: ProjectExt): GitRepositoryManager {
        return GitRepositoryManager.getInstance(project.delegate())
    }
}