package com.duxtinto.intellij.plugin.github.codereviews.domain.issues;

import com.google.auto.value.AutoValue;

import java.net.URL;

@AutoValue
public abstract class IssueEntity {
    public abstract long number();
    public abstract URL url();

    public static IssueEntity create(long number, URL url) {
        return new AutoValue_IssueEntity(number, url);
    }
}
