package com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow.pullrequestlist.columns;

import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.PullRequestEntity;
import com.intellij.util.ui.ColumnInfo;
import org.jetbrains.annotations.Nullable;

import java.util.stream.Collectors;

class IssuesColumnInfo extends ColumnInfo<PullRequestEntity, String> {
    IssuesColumnInfo() {
        super("issues");
    }

    @Nullable
    @Override
    public String valueOf(PullRequestEntity pullRequest) {
        return pullRequest.closableIssues()
                .stream()
                .map(issue -> String.valueOf(issue.number()))
                .collect(Collectors.joining(","));
    }
}
