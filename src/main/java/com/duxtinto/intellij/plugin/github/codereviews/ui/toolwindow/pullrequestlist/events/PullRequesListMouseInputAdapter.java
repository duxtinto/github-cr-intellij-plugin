package com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow.pullrequestlist.events;

import com.duxtinto.intellij.plugin.github.codereviews.ide.acl.entities.GithubPullRequestExt;
import com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow.pullrequestlist.ColumnInfoFactory;
import com.intellij.ide.BrowserUtil;
import com.intellij.ui.table.TableView;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.swing.event.MouseInputAdapter;
import java.awt.event.MouseEvent;

public class PullRequesListMouseInputAdapter extends MouseInputAdapter {

    @Nullable
    private
    TableView<GithubPullRequestExt> table;

    public void listenToTable(@Nonnull TableView<GithubPullRequestExt> table) {
        if (this.table != null) {
            this.table.removeMouseListener(this);
        }

        this.table = table;
        this.table.addMouseListener(this);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (table != null) {
            int row = table.rowAtPoint(e.getPoint());
            int column = table.columnAtPoint(e.getPoint());
            if (row != -1 && column == ColumnInfoFactory.ColumnIndexes.NUMBER.ordinal()) {
                BrowserUtil.browse(table.getRow(row).getUrl());
            }
        }
    }
}
