package com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow.pullrequestlist.columns;

import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.PullRequestEntity;
import com.intellij.util.ui.ColumnInfo;

import javax.inject.Inject;

public class ColumnInfoFactory {
    @Inject
    public ColumnInfoFactory() {
    }

    public enum ColumnIndexes {
        NUMBER,
        ISSUE,
        STATE,
        TITLE,
    }

    public ColumnInfo<PullRequestEntity, ?>[] createDefaultColumns() {
        return new ColumnInfo[]{
                new NumberColumnInfo(),
                new IssuesColumnInfo(),
                new StateColumnInfo(),
                new TitleColumnInfo(),
        };
    }
}
