package com.duxtinto.intellij.plugin.github.codereviews.ide.acl.services;

import com.duxtinto.intellij.plugin.github.codereviews.di.scopes.ProjectScoped;
import com.duxtinto.intellij.plugin.github.codereviews.domain.issues.IssueEntity;
import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.PullRequestEntity;
import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.issues.GetAllClosableByInteractor;
import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.issues.GetAllClosableByRequest;
import com.duxtinto.intellij.plugin.github.codereviews.ide.acl.entities.GithubConnectionExt;
import org.apache.http.Header;
import org.apache.http.message.BasicHeader;
import org.jetbrains.plugins.github.api.GithubConnection;
import org.jetbrains.plugins.github.api.data.GithubPullRequest;

import javax.annotation.Nonnull;
import javax.inject.Inject;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.checkNotNull;

@ProjectScoped
public class GithubApiV3PullRequestLoader {
    private static final Header ACCEPT_V3_JSON_HTML_MARKUP = new BasicHeader("Accept", "application/vnd.github.v3.html+json");

    @Nonnull
    private final GithubConnectionExt connection;
    @Nonnull
    private final GetAllClosableByInteractor getClosableIssuesInteractor;

    @Inject
    public GithubApiV3PullRequestLoader(
            @Nonnull GithubConnectionExt connection,
            @Nonnull GetAllClosableByInteractor getClosableIssues
    ) {
        this.connection = connection;
        this.getClosableIssuesInteractor = checkNotNull(getClosableIssues);
    }

    @Nonnull
    public List<PullRequestEntity> loadAllPullRequests(@Nonnull String path) throws IOException {
        return loadAll(connection, path, GithubPullRequest[].class, ACCEPT_V3_JSON_HTML_MARKUP)
                .stream()
                .map((idePullRequest) -> {
                            try {
                                return new PullRequestEntity(
                                        idePullRequest.getNumber(),
                                        getClosableIssues(idePullRequest.getBodyHtml()),
                                        idePullRequest.getTitle(),
                                        PullRequestEntity.State.Companion.fromString(idePullRequest.getState()),
                                        new URL(idePullRequest.getHtmlUrl()));
                            } catch (MalformedURLException e) {
                                e.printStackTrace();
                                return null;
                            }
                        }
                )
                .collect(Collectors.toList());
    }

    private List<IssueEntity> getClosableIssues(String pullRequestDescriptionText) {
        return getClosableIssuesInteractor.run(new GetAllClosableByRequest(pullRequestDescriptionText));
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
