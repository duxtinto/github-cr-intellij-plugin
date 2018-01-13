package com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow.pullrequestlist;

import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.PullRequestEntity;
import com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow.pullrequestlist.events.PullRequesListMouseInputAdapter;
import com.intellij.ui.components.JBScrollPane;
import com.intellij.ui.table.TableView;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.util.ui.JBUI;

import javax.swing.*;

public class PullRequestListView implements PullRequestList.View {
    private TableView<PullRequestEntity> table;
    private JPanel content;

    private final PullRequestList.Presenter presenter;

    public PullRequestListView(PullRequestList.Presenter presenter, PullRequesListMouseInputAdapter mouseInputAdapter) {
        this.presenter = presenter;
        table = new TableView<>(presenter.getModel().getTableModel());
        table
                .getEmptyText()
                .clear()
                .appendText("Nothing to see here.");

        content = new JPanel();
        content.setLayout(new GridLayoutManager(1, 1, JBUI.emptyInsets(), -1, -1));
        final JBScrollPane scrollPane1 = new JBScrollPane();
        content.add(scrollPane1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        scrollPane1.setViewportView(table);

        mouseInputAdapter.listenToTable(table);
    }

    @Override
    public void render(PullRequestList.Model model) {
        table.setModelAndUpdateColumns(model.getTableModel());
        table.repaint();
    }

    @Override
    public JComponent getContent() {
        return content;
    }
}
