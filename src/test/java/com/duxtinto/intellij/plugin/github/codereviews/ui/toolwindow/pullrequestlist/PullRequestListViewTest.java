package com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow.pullrequestlist;

import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.PullRequestEntity;
import com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow.pullrequestlist.columns.NumberColumnInfo;
import com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow.pullrequestlist.columns.TitleColumnInfo;
import com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow.pullrequestlist.events.PullRequestListMouseInputAdapter;
import com.intellij.ui.components.JBScrollPane;
import com.intellij.ui.table.TableView;
import com.intellij.util.ui.ColumnInfo;
import com.intellij.util.ui.ListTableModel;
import com.intellij.util.ui.StatusText;
import mockit.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.inject.Named;
import javax.swing.*;

class PullRequestListViewTest {

    @Injectable
    @Named("default")
    private ColumnInfo[] columnInfos = new ColumnInfo[] {
            new NumberColumnInfo(),
            new TitleColumnInfo(),
    };

    @Injectable
    private PullRequestListMouseInputAdapter mouseInputAdapter;

    @Test
    @DisplayName("render, if content has previously being created, should update & repaint the table and its content")
    void renderHappyPath(
            @Injectable TableView<PullRequestEntity> table,
            @Injectable JPanel content,
            @Mocked PullRequestList.Model model,
            @Tested PullRequestListView view) {
        final ListTableModel<PullRequestEntity> tableModel = new ListTableModel<>();
        new Expectations() {{
            model.getTableModel(); result = tableModel;
        }};

        view.render(model);

        new VerificationsInOrder() {{
            table.setModelAndUpdateColumns(tableModel);
            table.repaint();
        }};

        new FullVerifications(mouseInputAdapter) {{}};
    }

    @Test
    @DisplayName("render, if content has not been previously initialized, should initialize content and set the listener")
    void renderShouldInitializeContent(
            @Mocked JPanel content,
            @Mocked TableView<PullRequestEntity> table,
            @Mocked JBScrollPane scrollPane,
            @Mocked StatusText emptyText,
            @Mocked PullRequestList.Model model,
            @Tested PullRequestListView view) {
        final ListTableModel<PullRequestEntity> tableModel = new ListTableModel<>();
        new Expectations() {{
            new JPanel(); result = content;
            new TableView<>((ListTableModel<?>)any); result = table;
            new JBScrollPane();
        }};

        new Expectations() {{
            table.getEmptyText(); result = emptyText;
            model.getTableModel(); result = tableModel;
        }};

        view.render(model);

        new Verifications() {{
            mouseInputAdapter.listenToTable(table);
        }};
    }

    @Test
    @DisplayName("get content, if content hasn't been initialized before, should initialize content and set the listener")
    void getContentShouldInitializeContent(
            @Mocked JPanel content,
            @Mocked TableView<PullRequestEntity> table,
            @Mocked JBScrollPane scrollPane,
            @Mocked StatusText emptyText,
            @Tested PullRequestListView view) {
        new Expectations() {{
            new JPanel(); result = content;
            new TableView<>((ListTableModel<?>)any); result = table;
            new JBScrollPane();
        }};

        new Expectations() {{
            table.getEmptyText(); result = emptyText;
        }};

        view.getContent();

        new Verifications() {{
            mouseInputAdapter.listenToTable(table);
        }};
    }
}