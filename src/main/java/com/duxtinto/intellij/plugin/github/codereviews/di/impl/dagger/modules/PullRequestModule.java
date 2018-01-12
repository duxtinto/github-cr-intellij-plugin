package com.duxtinto.intellij.plugin.github.codereviews.di.impl.dagger.modules;

import com.duxtinto.intellij.plugin.github.codereviews.data.pullrequests.PullRequestRepositoryImpl;
import com.duxtinto.intellij.plugin.github.codereviews.di.scopes.ProjectScoped;
import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.contracts.PullRequestRepository;
import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.interactors.GetAllOpenForRepoInteractor;
import com.duxtinto.intellij.plugin.github.codereviews.ide.acl.entities.GithubConnectionExt;
import com.duxtinto.intellij.plugin.github.codereviews.ide.acl.services.GithubApiLoader;
import com.duxtinto.intellij.plugin.github.codereviews.net.pullrequests.PullRequestFetcher;
import dagger.Module;
import dagger.Provides;

@Module
public class PullRequestModule {
    @Provides
    @ProjectScoped
    public GithubApiLoader provideGithubApiLoader() {
        return new GithubApiLoader();
    }

    @Provides
    @ProjectScoped
    public PullRequestFetcher providePullRequestFetcher(GithubConnectionExt connection, GithubApiLoader apiLoader) {
        return new PullRequestFetcher(connection, apiLoader);
    }

    @Provides
    @ProjectScoped
    public PullRequestRepository providePullRequestRepository(PullRequestFetcher fetcher) {
        return new PullRequestRepositoryImpl(fetcher);
    }

    @Provides
    @ProjectScoped
    public GetAllOpenForRepoInteractor provideGetAllOpenForRepoInteractor(PullRequestRepository pullRequestRepository) {
        return new GetAllOpenForRepoInteractor(pullRequestRepository);
    }
}
