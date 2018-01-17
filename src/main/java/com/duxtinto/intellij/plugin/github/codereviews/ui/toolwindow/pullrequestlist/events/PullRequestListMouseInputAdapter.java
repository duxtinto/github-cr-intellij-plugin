package com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow.pullrequestlist.events;

import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.PullRequestEntity;
import com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow.pullrequestlist.columns.ColumnInfoFactory;
import com.intellij.ide.BrowserUtil;
import com.intellij.ui.table.TableView;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.swing.event.MouseInputAdapter;
import java.awt.event.MouseEvent;

import static com.google.common.base.Preconditions.checkNotNull;

public class PullRequestListMouseInputAdapter extends MouseInputAdapter {

    @Nullable
    private TableView<PullRequestEntity> table;

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
            int row = table.rowAtPoint(e.getPoint());
            int column = table.columnAtPoint(e.getPoint());
            if (row != -1 && column == ColumnInfoFactory.ColumnIndexes.NUMBER.ordinal()) {
                BrowserUtil.browse(table.getRow(row).url());
            }
        }
    }
}
