package com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests;

import com.google.auto.value.AutoValue;
import com.google.common.base.Enums;

@AutoValue
public abstract class PullRequestEntity {
    public abstract long number();
    public abstract String title();
    public abstract State state();
    public abstract String url();

    public static PullRequestEntity create(long number, String title, State state, String ulr) {
        return new AutoValue_PullRequestEntity(number, title, state, ulr);
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
