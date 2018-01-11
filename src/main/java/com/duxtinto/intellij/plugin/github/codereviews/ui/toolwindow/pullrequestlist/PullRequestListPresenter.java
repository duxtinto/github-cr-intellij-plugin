package com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow.pullrequestlist;

import com.duxtinto.intellij.plugin.github.codereviews.ide.acl.entities.GithubPullRequestExt;

import java.util.List;

public class PullRequestListPresenter implements PullRequestList.Presenter {
    private final PullRequestList.View view;
    private PullRequestList.Model model;

    public PullRequestListPresenter(PullRequestList.View view) {
        this.view = view;
        this.model = new PullRequestListModel();
    }

    public void displayPullRequests(List<GithubPullRequestExt> pullRequests) {
        updateModel(pullRequests);
        view.render(model);
    }

    private void updateModel(List<GithubPullRequestExt> pullRequests) {

    }
}
