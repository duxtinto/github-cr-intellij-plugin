package com.duxtinto.intellij.plugin.github.codereviews.events.repos;

import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.GetAllOpenForRepoInteractor;
import com.duxtinto.intellij.plugin.github.codereviews.domain.repositories.GithubRepositoryEntity;
import com.duxtinto.intellij.plugin.github.codereviews.domain.repositories.FindGithubRepoForRootFolderInteractor;
import com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow.pullrequestlist.PullRequestList;
import com.intellij.dvcs.repo.VcsRepositoryMappingListener;

import javax.annotation.Nonnull;
import javax.inject.Inject;

import static com.google.common.base.Preconditions.checkNotNull;

public class GitChangeListener implements VcsRepositoryMappingListener {
    @Nonnull
    private final FindGithubRepoForRootFolderInteractor githubRepoFinder;
    @Nonnull
    private final GetAllOpenForRepoInteractor pullRequestFetcher;
    @Nonnull
    private final PullRequestList.Presenter pullRequestsPresenter;

    @Inject
    public GitChangeListener(
            @Nonnull FindGithubRepoForRootFolderInteractor githubRepoFinder,
            @Nonnull GetAllOpenForRepoInteractor pullRequestFetcher,
            @Nonnull PullRequestList.Presenter pullRequestsPresenter) {
        this.githubRepoFinder = checkNotNull(githubRepoFinder);
        this.pullRequestFetcher = checkNotNull(pullRequestFetcher);
        this.pullRequestsPresenter = checkNotNull(pullRequestsPresenter);
    }

    @Override
    public void mappingChanged() {
        final GithubRepositoryEntity repo = githubRepoFinder.run(null);
        if (repo != null) {
            pullRequestsPresenter.displayPullRequests(pullRequestFetcher.run(repo));
        }
    }
}
