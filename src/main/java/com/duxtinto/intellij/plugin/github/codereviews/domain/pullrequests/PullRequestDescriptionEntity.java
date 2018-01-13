package com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests;

import com.google.auto.value.AutoValue;

import java.net.URL;
import java.util.Map;

@AutoValue
public abstract class PullRequestDescriptionEntity {
    // Map<Issue number, Issue Url>
    public abstract Map<Long, URL> closableIssues();

    public static PullRequestDescriptionEntity create(Map<Long, URL> closableIssues) {
        return new AutoValue_PullRequestDescriptionEntity(closableIssues);
    }
}
