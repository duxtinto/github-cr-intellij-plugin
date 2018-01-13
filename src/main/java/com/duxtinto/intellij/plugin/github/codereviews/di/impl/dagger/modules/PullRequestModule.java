package com.duxtinto.intellij.plugin.github.codereviews.di.impl.dagger.modules;

import com.duxtinto.intellij.plugin.github.codereviews.data.pullrequests.PullRequestRepositoryImpl;
import com.duxtinto.intellij.plugin.github.codereviews.di.scopes.ProjectScoped;
import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.PullRequestDomainContract;
import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.GetAllOpenForRepoInteractor;
import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.issues.GetAllClosableByInteractor;
import com.duxtinto.intellij.plugin.github.codereviews.ide.acl.entities.GithubConnectionExt;
import com.duxtinto.intellij.plugin.github.codereviews.ide.acl.services.GithubApiV3Loader;
import com.duxtinto.intellij.plugin.github.codereviews.net.pullrequests.ApiV3PullRequestFetcher;
import com.duxtinto.intellij.plugin.github.codereviews.services.pullrequests.GithubDescriptionParser;
import dagger.Module;
import dagger.Provides;

@Module
public class PullRequestModule {
    @Provides
    @ProjectScoped
    public GithubApiV3Loader provideGithubApiLoader(GetAllClosableByInteractor interactor) {
        return new GithubApiV3Loader(interactor);
    }

    @Provides
    @ProjectScoped
    public PullRequestDomainContract.Fetcher providePullRequestFetcher(
            GithubConnectionExt connection,
            GithubApiV3Loader apiLoader) {
        return new ApiV3PullRequestFetcher(connection, apiLoader);
    }

    @Provides
    @ProjectScoped
    public PullRequestDomainContract.Repository providePullRequestRepository(PullRequestDomainContract.Fetcher fetcher) {
        return new PullRequestRepositoryImpl((ApiV3PullRequestFetcher)fetcher);
    }

    @Provides
    @ProjectScoped
    public PullRequestDomainContract.DescriptionParser providePullRequestDescriptionParser() {
        return new GithubDescriptionParser();
    }

    @Provides
    @ProjectScoped
    public GetAllClosableByInteractor provideGetClosableIssuesInteractor(
            PullRequestDomainContract.DescriptionParser descriptionParser
    ) {
        return new GetAllClosableByInteractor(descriptionParser);
    }

    @Provides
    @ProjectScoped
    public GetAllOpenForRepoInteractor provideGetAllOpenForRepoInteractor(PullRequestDomainContract.Repository repository) {
        return new GetAllOpenForRepoInteractor(repository);
    }
}
