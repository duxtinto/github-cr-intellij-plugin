package com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow.pullrequestlist;

import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.PullRequestEntity;
import com.intellij.util.ui.ColumnInfo;
import com.intellij.util.ui.ListTableModel;

import java.util.List;

public class PullRequestListModel implements PullRequestList.Model {
    private ListTableModel<PullRequestEntity> tableModel;

    PullRequestListModel(ColumnInfo[] columns) {
        this.tableModel = new ListTableModel<>(columns);
    }

    @Override
    public ListTableModel<PullRequestEntity> getTableModel() {
        return tableModel;
    }

    @Override
    public void setPullRequests(List<PullRequestEntity> pullRequests) {
        tableModel.setItems(pullRequests);
    }
}
