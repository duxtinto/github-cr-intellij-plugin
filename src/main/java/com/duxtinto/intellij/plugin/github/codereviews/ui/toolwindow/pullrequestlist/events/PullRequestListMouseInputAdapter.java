package com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow.pullrequestlist.events;

import com.duxtinto.intellij.plugin.github.codereviews.di.scopes.ProjectScoped;
import com.duxtinto.intellij.plugin.github.codereviews.domain.issues.IssueEntity;
import com.duxtinto.intellij.plugin.github.codereviews.domain.issues.SwitchToIssueInteractor;
import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.PullRequestEntity;
import com.intellij.ide.BrowserUtil;
import com.intellij.ui.table.TableView;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.inject.Inject;
import javax.swing.event.MouseInputAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import static com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow.pullrequestlist.columns.ColumnInfoFactory.ColumnIndexes.ISSUE;
import static com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow.pullrequestlist.columns.ColumnInfoFactory.ColumnIndexes.NUMBER;
import static com.google.common.base.Preconditions.checkNotNull;

@ProjectScoped
public class PullRequestListMouseInputAdapter extends MouseInputAdapter {

    @Nullable
    private TableView<PullRequestEntity> table;
    @Nonnull
    private final SwitchToIssueInteractor issueContextSwitcher;

    @Inject
    public PullRequestListMouseInputAdapter(@Nonnull SwitchToIssueInteractor issueContextSwitcher) {
        this.issueContextSwitcher = checkNotNull(issueContextSwitcher);
    }

    public void listenToTable(@Nonnull TableView<PullRequestEntity> table) {
        checkNotNull(table);

        if (this.table != null) {
            this.table.removeMouseListener(this);
        }

        this.table = table;
        this.table.addMouseListener(this);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (table!=null && table.equals(e.getSource())) {
            int rowIndex = table.rowAtPoint(e.getPoint());
            int columnIndex = table.columnAtPoint(e.getPoint());
            if (rowIndex != -1) {
                if (columnIndex == NUMBER.ordinal()) {
                    BrowserUtil.browse(table.getRow(rowIndex).getUrl());
                }

                if (columnIndex == ISSUE.ordinal()) {
                    final List<IssueEntity> closableIssues = table.getRow(rowIndex).getCloseableIssues();
                    if (!closableIssues.isEmpty()) {
                        issueContextSwitcher.run(closableIssues.get(0));
                    }
                }
            }
        }
    }
}
