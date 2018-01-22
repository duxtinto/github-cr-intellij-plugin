package com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow.pullrequestlist.columns;

import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.PullRequestEntity;
import com.intellij.util.ui.ColumnInfo;
import org.jetbrains.annotations.Nullable;

import java.util.stream.Collectors;

public class IssuesColumnInfo extends ColumnInfo<PullRequestEntity, String> {
    public IssuesColumnInfo() {
        super("issues");
    }

    @Nullable
    @Override
    public String valueOf(PullRequestEntity pullRequest) {
        return pullRequest.getCloseableIssues()
                .stream()
                .map(issue -> String.valueOf(issue.getNumber()))
                .collect(Collectors.joining(","));
    }
}
