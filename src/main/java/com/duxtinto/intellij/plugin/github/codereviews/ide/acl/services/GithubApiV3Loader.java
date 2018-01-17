package com.duxtinto.intellij.plugin.github.codereviews.ide.acl.services;

import com.duxtinto.intellij.plugin.github.codereviews.di.scopes.ProjectScoped;
import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.PullRequestEntity;
import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.issues.GetAllClosableByInteractor;
import com.duxtinto.intellij.plugin.github.codereviews.ide.acl.entities.GithubConnectionExt;
import org.apache.http.Header;
import org.apache.http.message.BasicHeader;
import org.jetbrains.plugins.github.api.GithubConnection;
import org.jetbrains.plugins.github.api.data.GithubPullRequest;

import javax.annotation.Nonnull;
import javax.inject.Inject;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.checkNotNull;

@ProjectScoped
public class GithubApiV3Loader {
    private static final Header ACCEPT_V3_JSON_HTML_MARKUP = new BasicHeader("Accept", "application/vnd.github.v3.html+json");

    @Nonnull
    private final GetAllClosableByInteractor getClosableIssues;

    @Inject
    public GithubApiV3Loader(@Nonnull GetAllClosableByInteractor getClosableIssues) {
        this.getClosableIssues = checkNotNull(getClosableIssues);
    }

    @Nonnull
    public List<PullRequestEntity> loadAllPullRequests(
            @Nonnull GithubConnectionExt connection,
            @Nonnull String path) throws IOException {
        return loadAll(connection, path, GithubPullRequest[].class, ACCEPT_V3_JSON_HTML_MARKUP)
                .stream()
                .map((idePullRequest) -> PullRequestEntity.create(
                        idePullRequest.getNumber(),
                        getClosableIssues.run(idePullRequest.getBodyHtml()),
                        idePullRequest.getTitle(),
                        PullRequestEntity.State.fromString(idePullRequest.getState()),
                        idePullRequest.getHtmlUrl())
                )
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
