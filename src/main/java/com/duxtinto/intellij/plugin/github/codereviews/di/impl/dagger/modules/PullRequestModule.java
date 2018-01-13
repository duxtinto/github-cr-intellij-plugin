package com.duxtinto.intellij.plugin.github.codereviews.di.impl.dagger.modules;

import com.duxtinto.intellij.plugin.github.codereviews.data.pullrequests.PullRequestRepositoryImpl;
import com.duxtinto.intellij.plugin.github.codereviews.di.scopes.ProjectScoped;
import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.contracts.PullRequestFetcher;
import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.contracts.PullRequestRepository;
import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.interactors.GetAllOpenForRepoInteractor;
import com.duxtinto.intellij.plugin.github.codereviews.ide.acl.entities.GithubConnectionExt;
import com.duxtinto.intellij.plugin.github.codereviews.ide.acl.services.GithubApiV3Loader;
import com.duxtinto.intellij.plugin.github.codereviews.net.pullrequests.ApiV3PullRequestFetcher;
import dagger.Module;
import dagger.Provides;

@Module
public class PullRequestModule {
    @Provides
    @ProjectScoped
    public GithubApiV3Loader provideGithubApiLoader() {
        return new GithubApiV3Loader();
    }

    @Provides
    @ProjectScoped
    public PullRequestFetcher providePullRequestFetcher(
            GithubConnectionExt connection,
            GithubApiV3Loader apiLoader) {
        return new ApiV3PullRequestFetcher(connection, apiLoader);
    }

    @Provides
    @ProjectScoped
    public PullRequestRepository providePullRequestRepository(PullRequestFetcher fetcher) {
        return new PullRequestRepositoryImpl((ApiV3PullRequestFetcher)fetcher);
    }

    @Provides
    @ProjectScoped
    public GetAllOpenForRepoInteractor provideGetAllOpenForRepoInteractor(PullRequestRepository pullRequestRepository) {
        return new GetAllOpenForRepoInteractor(pullRequestRepository);
    }
}
