package com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow;

import com.duxtinto.intellij.plugin.github.codereviews.di.contracts.DiContainerAware;
import com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow.pullrequestlist.PullRequestList;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.impl.ToolWindowImpl;
import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;
import javax.inject.Named;

public class ToolWindowFactory implements com.intellij.openapi.wm.ToolWindowFactory, DiContainerAware, DumbAware {
    @Inject
    @Named("GH_Reviews")
    ToolWindowImpl myToolWindow;

    @Inject
    ToolWindowContent.View view;

    @Inject
    ToolWindowContent.Presenter presenter;

    @Inject
    PullRequestList.View pullRequestListView;

    @Inject
    PullRequestList.Presenter pullRequestListPresenter;

    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
        getProjectContainer(project).inject(this);

        if (!myToolWindow.getId().equals(((ToolWindowImpl)toolWindow).getId())) {
            return;
        }

        presenter.setView(view);
        presenter.displayContent();

        pullRequestListPresenter.setView(pullRequestListView);
    }
}
