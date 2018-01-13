package com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow.pullrequestlist;

import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.PullRequestEntity;

import java.util.List;

public class PullRequestListPresenter implements PullRequestList.Presenter {
    private PullRequestList.View view;
    private final PullRequestList.Model model;

        public PullRequestListPresenter(ColumnInfoFactory columnInfoFactory) {
        this.model = new PullRequestListModel(columnInfoFactory.createDefaultColumns());
    }

    @Override
    public void displayPullRequests(List<PullRequestEntity> pullRequests) {
        updateModel(pullRequests);
        if (view != null) {
            view.render(model);
        }
    }

    @Override
    public PullRequestList.Model getModel() {
        return model;
    }

    private void updateModel(List<PullRequestEntity> pullRequests) {
        model.setPullRequests(pullRequests);
    }

    public void setView(PullRequestList.View view) {
        this.view = view;
    }
}
