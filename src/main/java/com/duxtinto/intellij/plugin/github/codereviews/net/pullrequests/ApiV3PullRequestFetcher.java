package com.duxtinto.intellij.plugin.github.codereviews.net.pullrequests;

import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.PullRequestDomainContract;
import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.PullRequestEntity;
import com.duxtinto.intellij.plugin.github.codereviews.ide.acl.services.GithubApiV3PullRequestLoader;

import javax.annotation.Nonnull;
import javax.inject.Inject;
import java.io.IOException;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

public class ApiV3PullRequestFetcher implements PullRequestDomainContract.Fetcher {
    private final GithubApiV3PullRequestLoader apiLoader;

    @Inject
    public ApiV3PullRequestFetcher(@Nonnull GithubApiV3PullRequestLoader apiLoader) {
        this.apiLoader = checkNotNull(apiLoader);
    }

    @Override
    public List<PullRequestEntity> fetchForRepository(@Nonnull String userName, @Nonnull String repoName, PullRequestQueryParameters parameters) throws IOException {
        String path = "/repos/" + userName + "/" + repoName + "/pulls?" + parameters.toQueryString();
        return apiLoader.loadAllPullRequests(path);
    }
}
