package com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow.pullrequestlist.columns;

import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.PullRequestEntity;
import com.intellij.util.ui.ColumnInfo;
import org.jetbrains.annotations.Nullable;

public class StateColumnInfo extends ColumnInfo<PullRequestEntity, String> {
    public StateColumnInfo() {
        super("state");
    }

    @Nullable
    @Override
    public String valueOf(PullRequestEntity pullRequest) {
        return pullRequest.getState().toString();
    }
}
