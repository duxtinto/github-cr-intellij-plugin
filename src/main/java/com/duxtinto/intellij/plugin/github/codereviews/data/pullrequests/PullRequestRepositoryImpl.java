package com.duxtinto.intellij.plugin.github.codereviews.data.pullrequests;

import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.PullRequestDomainContract;
import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.PullRequestEntity;
import com.duxtinto.intellij.plugin.github.codereviews.net.pullrequests.ApiV3PullRequestFetcher;
import com.duxtinto.intellij.plugin.github.codereviews.net.pullrequests.PullRequestQueryParameters;
import com.duxtinto.intellij.plugin.github.codereviews.net.pullrequests.PullRequestQueryParameters.State;

import javax.annotation.Nonnull;
import javax.inject.Inject;
import java.io.IOException;
import java.util.List;

public class PullRequestRepositoryImpl implements PullRequestDomainContract.Repository {
    private final ApiV3PullRequestFetcher fetcher;

    @Inject
    public PullRequestRepositoryImpl(@Nonnull PullRequestDomainContract.Fetcher fetcher) {
        this.fetcher = (ApiV3PullRequestFetcher) fetcher;
    }

    @Override
    public List<PullRequestEntity> getAllOpenBy(@Nonnull String userName, @Nonnull String repoName) throws IOException {
        PullRequestQueryParameters parameters = PullRequestQueryParameters.builder().setState(State.OPEN).build();
        return fetcher.fetchForRepository(userName, repoName, parameters);
    }
}
