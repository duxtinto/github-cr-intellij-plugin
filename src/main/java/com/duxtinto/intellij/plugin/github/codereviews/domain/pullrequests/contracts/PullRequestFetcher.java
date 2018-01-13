package com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.contracts;

import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.PullRequestEntity;
import com.duxtinto.intellij.plugin.github.codereviews.net.pullrequests.PullRequestQueryParameters;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.util.List;

public interface PullRequestFetcher {
    List<PullRequestEntity> fetchForRepository(@Nonnull String userName, @Nonnull String repoName, PullRequestQueryParameters parameters) throws IOException;
}
