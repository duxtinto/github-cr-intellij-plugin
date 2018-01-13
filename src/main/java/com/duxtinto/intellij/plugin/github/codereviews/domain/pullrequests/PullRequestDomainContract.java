package com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests;

import com.duxtinto.intellij.plugin.github.codereviews.net.pullrequests.PullRequestQueryParameters;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.util.List;

public interface PullRequestDomainContract {
    interface Fetcher {
        List<PullRequestEntity> fetchForRepository(@Nonnull String userName, @Nonnull String repoName, PullRequestQueryParameters parameters) throws IOException;
    }

    interface Repository {
        List<PullRequestEntity> getAllOpenBy(@Nonnull String userName, @Nonnull String repoName) throws IOException;
    }

    interface DescriptionParser {
        PullRequestDescriptionEntity parse(String description);
    }
}
