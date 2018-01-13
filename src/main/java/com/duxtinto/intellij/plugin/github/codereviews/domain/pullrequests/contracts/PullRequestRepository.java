package com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.contracts;

import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.PullRequestEntity;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.util.List;

public interface PullRequestRepository {
    List<PullRequestEntity> getAllOpenBy(@Nonnull String userName, @Nonnull String repoName) throws IOException;
}
