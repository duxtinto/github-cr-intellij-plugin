package com.duxtinto.intellij.plugin.github.codereviews.di.impl.dagger.modules;

import com.duxtinto.intellij.plugin.github.codereviews.di.scopes.ProjectScoped;
import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.interactors.GetAllOpenForRepoInteractor;
import com.duxtinto.intellij.plugin.github.codereviews.domain.repos.interactors.FindGithubRepoForRootFolderInteractor;
import com.duxtinto.intellij.plugin.github.codereviews.events.repos.GitChangeListener;
import com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow.pullrequestlist.PullRequestList;
import com.intellij.dvcs.repo.VcsRepositoryMappingListener;
import dagger.Module;
import dagger.Provides;

@Module()
public class EventsModule {
    @Provides
    @ProjectScoped
    public VcsRepositoryMappingListener provideVcsRepositoryMappingListener(
            FindGithubRepoForRootFolderInteractor githubRepoFinder,
            GetAllOpenForRepoInteractor pullRequestFetcher,
            PullRequestList.Presenter pullRequestPresenter) {
        return new GitChangeListener(githubRepoFinder, pullRequestFetcher, pullRequestPresenter);
    }
}
