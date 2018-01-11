package com.duxtinto.intellij.plugin.github.codereviews.ide.acl.services;

import com.duxtinto.intellij.plugin.github.codereviews.ide.acl.entities.GithubConnectionExt;
import com.duxtinto.intellij.plugin.github.codereviews.ide.acl.entities.GithubPullRequestExt;
import org.apache.http.Header;
import org.apache.http.message.BasicHeader;
import org.jetbrains.plugins.github.api.GithubConnection;
import org.jetbrains.plugins.github.api.data.GithubPullRequest;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class GithubApiLoader {
    private static final Header ACCEPT_V3_JSON_HTML_MARKUP = new BasicHeader("Accept", "application/vnd.github.v3.html+json");

    @Nonnull
    public List<GithubPullRequestExt> loadAllPullRequests(@Nonnull GithubConnectionExt connection,
                                                          @Nonnull String path) throws IOException {
        return loadAll(connection, path, GithubPullRequest[].class, ACCEPT_V3_JSON_HTML_MARKUP)
                .stream()
                .map(GithubPullRequestExt::create)
                .collect(Collectors.toList());
    }

    @Nonnull
    private <T> List<T> loadAll(@Nonnull GithubConnectionExt connection,
                                           @Nonnull String path,
                                           @Nonnull Class<? extends T[]> type,
                                           @Nonnull Header... headers) throws IOException {
        GithubConnection.PagedRequest<T> request = new GithubConnection.ArrayPagedRequest<>(path, type, headers);
        return request.getAll(connection.delegate());
    }
}
