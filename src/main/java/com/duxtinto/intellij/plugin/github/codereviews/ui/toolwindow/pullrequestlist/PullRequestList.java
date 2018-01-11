package com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow.pullrequestlist;

import com.duxtinto.intellij.plugin.github.codereviews.ide.acl.entities.GithubPullRequestExt;
import com.intellij.util.ui.ListTableModel;

import java.util.List;

public interface PullRequestList {
    interface Model {
        ListTableModel<GithubPullRequestExt> getTableModel();
        void setTableModel(ListTableModel<GithubPullRequestExt> tableModel);
    }
    interface View {
        void render(Model model);
    }
    interface Presenter {
        void displayPullRequests(List<GithubPullRequestExt> pullRequests);
    }
}
