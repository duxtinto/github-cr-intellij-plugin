package com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow.pullrequestlist;

import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.PullRequestEntity;
import com.intellij.util.ui.ListTableModel;

import javax.swing.*;
import java.util.List;

public interface PullRequestList {
    interface Model {
        ListTableModel<PullRequestEntity> getTableModel();
        void setPullRequests(List<PullRequestEntity> pullRequests);
    }
    interface View {
        void render(Model model);
        JComponent getContent();
    }
    interface Presenter {
        void displayPullRequests(List<PullRequestEntity> pullRequests);
        Model getModel();
        void setView(View view);
    }
}
