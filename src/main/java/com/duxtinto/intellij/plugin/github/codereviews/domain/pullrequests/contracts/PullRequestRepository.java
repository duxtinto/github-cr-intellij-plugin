package com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.contracts;

import com.duxtinto.intellij.plugin.github.codereviews.ide.acl.entities.GithubPullRequestExt;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.util.List;

public interface PullRequestRepository {
    List<GithubPullRequestExt> getAllOpenBy(@Nonnull String userName, @Nonnull String repoName) throws IOException;
}
