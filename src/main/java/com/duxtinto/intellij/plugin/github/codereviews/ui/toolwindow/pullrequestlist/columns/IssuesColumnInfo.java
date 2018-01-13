package com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow.pullrequestlist.columns;

import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.PullRequestEntity;
import com.intellij.util.ui.ColumnInfo;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

class IssuesColumnInfo extends ColumnInfo<PullRequestEntity, String> {
    IssuesColumnInfo() {
        super("issues");
    }

    @Nullable
    @Override
    public String valueOf(PullRequestEntity pullRequest) {
        return "TODO: Issues related to the PR";
    }
}
