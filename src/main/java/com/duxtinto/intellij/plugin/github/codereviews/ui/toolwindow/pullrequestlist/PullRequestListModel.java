package com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow.pullrequestlist;

import com.duxtinto.intellij.plugin.github.codereviews.ide.acl.entities.GithubPullRequestExt;
import com.intellij.util.ui.ColumnInfo;
import com.intellij.util.ui.ListTableModel;

import java.util.List;

public class PullRequestListModel implements PullRequestList.Model {
    private ListTableModel<GithubPullRequestExt> tableModel;

    PullRequestListModel(ColumnInfo[] columns) {
        this.tableModel = new ListTableModel<>(columns);
    }

    @Override
    public ListTableModel<GithubPullRequestExt> getTableModel() {
        return tableModel;
    }

    @Override
    public void setPullRequests(List<GithubPullRequestExt> pullRequests) {
        tableModel.setItems(pullRequests);
    }
}
