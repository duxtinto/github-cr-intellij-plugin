package com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests;

import com.duxtinto.intellij.plugin.github.codereviews.domain.issues.IssueEntity;
import com.google.auto.value.AutoValue;
import com.google.common.base.Enums;

import java.util.List;

@AutoValue
public abstract class PullRequestEntity {
    public abstract long number();
    public abstract List<IssueEntity> closableIssues();
    public abstract String title();
    public abstract State state();
    public abstract String url();

    public static PullRequestEntity create(long number, List<IssueEntity> closableIssues, String title, State state, String url) {
        return new AutoValue_PullRequestEntity(number, closableIssues, title, state, url);
    }

    public enum State {
        UNKNOWN,
        OPEN,
        CLOSED;

        public static State fromString(String string) {
            return Enums.getIfPresent(State.class, string.toUpperCase()).or(State.UNKNOWN);
        }
    }
}
