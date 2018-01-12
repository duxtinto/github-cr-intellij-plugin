package com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow.pullrequestlist;

import com.duxtinto.intellij.plugin.github.codereviews.ide.acl.entities.GithubPullRequestExt;
import com.intellij.util.ui.ListTableModel;

import javax.swing.*;
import java.util.List;

public interface PullRequestList {
    interface Model {
        ListTableModel<GithubPullRequestExt> getTableModel();
        void setPullRequests(List<GithubPullRequestExt> pullRequests);
    }
    interface View {
        void render(Model model);
        JComponent getContent();
    }
    interface Presenter {
        void displayPullRequests(List<GithubPullRequestExt> pullRequests);
        Model getModel();
        void setView(View view);
    }
}
