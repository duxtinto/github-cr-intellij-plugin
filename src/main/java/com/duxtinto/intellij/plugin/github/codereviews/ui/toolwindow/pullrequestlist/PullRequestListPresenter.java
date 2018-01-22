package com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow.pullrequestlist;

import com.duxtinto.intellij.plugin.github.codereviews.di.scopes.ProjectScoped;
import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.PullRequestEntity;
import com.intellij.util.ui.ColumnInfo;

import javax.annotation.Nonnull;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

@ProjectScoped
public class PullRequestListPresenter implements PullRequestList.Presenter {
    private PullRequestList.View view;
    private final PullRequestList.Model model;

    @Inject
    public PullRequestListPresenter(@Nonnull @Named("default") ColumnInfo<?, ?>[] columnInfos) {
        checkNotNull(columnInfos);

        this.model = new PullRequestListModel(columnInfos);
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

    public void setView(@Nonnull PullRequestList.View view) {
        this.view = checkNotNull(view);
    }
}
