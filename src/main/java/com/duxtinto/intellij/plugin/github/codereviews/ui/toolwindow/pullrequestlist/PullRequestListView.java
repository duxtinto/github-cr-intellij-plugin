package com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow.pullrequestlist;

import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.PullRequestEntity;
import com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow.pullrequestlist.events.PullRequestListMouseInputAdapter;
import com.intellij.ui.components.JBScrollPane;
import com.intellij.ui.table.TableView;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.util.ui.ColumnInfo;
import com.intellij.util.ui.JBUI;
import com.intellij.util.ui.ListTableModel;

import javax.annotation.Nonnull;
import javax.inject.Inject;
import javax.inject.Named;
import javax.swing.*;

import static com.google.common.base.Preconditions.checkNotNull;

public class PullRequestListView implements PullRequestList.View {
    private JPanel content;
    private TableView<PullRequestEntity> table;

    @Nonnull
    private final ColumnInfo[] defaultColumns;
    @Nonnull
    private final PullRequestListMouseInputAdapter mouseInputAdapter;

    @Inject
    public PullRequestListView(
            @Nonnull @Named("default") ColumnInfo[] defaultColumns,
            @Nonnull PullRequestListMouseInputAdapter mouseInputAdapter) {
        this.defaultColumns = checkNotNull(defaultColumns);
        this.mouseInputAdapter = checkNotNull(mouseInputAdapter);
    }

    @Override
    public void render(PullRequestList.Model model) {
        if (content == null) {
            initContent();
        }
        table.setModelAndUpdateColumns(model.getTableModel());
        table.repaint();
    }

    private void initContent() {
        content = new JPanel();

        content.setLayout(new GridLayoutManager(1, 1, JBUI.emptyInsets(), -1, -1));
        final JBScrollPane scrollPane1 = new JBScrollPane();
        content.add(scrollPane1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        if (table == null) {
            initEmptyTable();
            mouseInputAdapter.listenToTable(table);
        }
        scrollPane1.setViewportView(table);
    }

    private void initEmptyTable() {
        table = new TableView<>(new ListTableModel<>(defaultColumns));
        table.getEmptyText()
                .clear()
                .appendText("Nothing to see here.");
    }

    @Override
    public JComponent getContent() {
        if (content == null) {
            initContent();
        }
        return content;
    }
}
