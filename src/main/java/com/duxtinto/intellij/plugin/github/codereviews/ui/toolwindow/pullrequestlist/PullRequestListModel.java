package com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow.pullrequestlist;

import com.duxtinto.intellij.plugin.github.codereviews.ide.acl.entities.GithubPullRequestExt;
import com.intellij.util.ui.ListTableModel;

public class PullRequestListModel implements PullRequestList.Model {
    private ListTableModel<GithubPullRequestExt> tableModel;

    PullRequestListModel() {
        this.tableModel = new ListTableModel<>();
    }

    @Override
    public ListTableModel<GithubPullRequestExt> getTableModel() {
        return tableModel;
    }

    @Override
    public void setTableModel(ListTableModel<GithubPullRequestExt> tableModel) {
        this.tableModel = tableModel;
    }
}
