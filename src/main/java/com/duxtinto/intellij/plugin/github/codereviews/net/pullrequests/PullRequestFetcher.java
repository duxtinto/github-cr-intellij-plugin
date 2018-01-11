package com.duxtinto.intellij.plugin.github.codereviews.net.pullrequests;

import com.duxtinto.intellij.plugin.github.codereviews.ide.acl.entities.GithubConnectionExt;
import com.duxtinto.intellij.plugin.github.codereviews.ide.acl.entities.GithubPullRequestExt;
import com.duxtinto.intellij.plugin.github.codereviews.ide.acl.services.GithubApiLoader;
import org.apache.http.Header;
import org.apache.http.message.BasicHeader;
import org.jetbrains.plugins.github.api.data.GithubPullRequest;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

public class PullRequestFetcher {
    private final GithubConnectionExt connection;
    private final GithubApiLoader apiLoader;

    public PullRequestFetcher(@Nonnull GithubConnectionExt connection, @Nonnull GithubApiLoader apiLoader) {
        this.connection = checkNotNull(connection);
        this.apiLoader = checkNotNull(apiLoader);
    }

    public List<GithubPullRequestExt> fetchForRepository(@Nonnull String userName, @Nonnull String repoName, PullRequestQueryParameters parameters) throws IOException {
        String path = "/repos/" + userName + "/" + repoName + "/pulls?" + parameters.toQueryString();
        return apiLoader.loadAllPullRequests(connection, path);
    }
}
