package com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow.pullrequestlist;

import com.duxtinto.intellij.plugin.github.codereviews.ide.acl.entities.GithubPullRequestExt;
import com.intellij.util.ui.ColumnInfo;
import org.jetbrains.annotations.Nullable;

public class ColumnInfoFactory {
    public ColumnInfo[] createDefaultColumns() {
        return new ColumnInfo[] {
               new NumberColumnInfo(),
               new StateColumnInfo(),
               new TitleColumnInfo(),
        };
    }

    private class NumberColumnInfo extends ColumnInfo<GithubPullRequestExt, Long> {
        NumberColumnInfo() {
            super("number");
        }

        @Nullable
        @Override
        public Long valueOf(GithubPullRequestExt pullRequest) {
            return pullRequest.getNumber();
        }
    }

    private class StateColumnInfo extends ColumnInfo<GithubPullRequestExt, String> {
        StateColumnInfo() {
            super("state");
        }

        @Nullable
        @Override
        public String valueOf(GithubPullRequestExt pullRequest) {
            return pullRequest.getState().toString();
        }
    }

    private class TitleColumnInfo extends ColumnInfo<GithubPullRequestExt, String> {
        TitleColumnInfo() {
            super("title");
        }

        @Nullable
        @Override
        public String valueOf(GithubPullRequestExt pullRequest) {
            return pullRequest.getTitle();
        }
    }
}
