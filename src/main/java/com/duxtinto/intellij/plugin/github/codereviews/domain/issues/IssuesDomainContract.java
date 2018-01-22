package com.duxtinto.intellij.plugin.github.codereviews.domain.issues;

import org.jetbrains.annotations.Nullable;

public interface IssuesDomainContract {
    interface Switcher {
        void switchToIssue(IssueEntity issue);
    }
    interface Fetcher {
        @Nullable
        IssueEntity fetchIssueById(long id);
    }
}
