package com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow;

import com.duxtinto.intellij.plugin.github.codereviews.di.contracts.DiContainerAware;
import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.interactors.GetAllOpenForRepoInteractor;
import com.duxtinto.intellij.plugin.github.codereviews.domain.repos.GithubRepositoryEntity;
import com.duxtinto.intellij.plugin.github.codereviews.domain.repos.interactors.FindGithubRepoForRootFolderInteractor;
import com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow.pullrequestlist.PullRequestList;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.impl.ToolWindowImpl;
import com.intellij.ui.content.ContentFactory;
import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;
import javax.inject.Named;

public class ToolWindowFactory implements com.intellij.openapi.wm.ToolWindowFactory, DiContainerAware, DumbAware {

    @Inject
    FindGithubRepoForRootFolderInteractor githubRepoFinder;

    @Inject
    GetAllOpenForRepoInteractor pullRequestFetcher;

    @Inject
    ContentFactory contentFactory;

    @Inject
    @Named("GH_Reviews")
    ToolWindowImpl myToolWindow;

    @Inject
    ToolWindowContent.View view;

    @Inject
    ToolWindowContent.Presenter presenter;

    @Inject
    PullRequestList.Presenter pullRequestsPresenter;

    @Inject
    PullRequestList.View pullRequestsView;

    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
        getProjectContainer(project).inject(this);

        if (!myToolWindow.getId().equals(((ToolWindowImpl)toolWindow).getId())) {
            return;
        }

        presenter.setView(view);
        presenter.displayContent();

        pullRequestsPresenter.setView(pullRequestsView);
        displayPullRequests();

        ((ToolWindowContentView)view).getReloadButton().addActionListener(actionEvent -> {
            displayPullRequests();
        });
    }

    private void displayPullRequests() {
        final GithubRepositoryEntity repo = githubRepoFinder.run(null);
        if (repo != null) {
            pullRequestsPresenter.displayPullRequests(pullRequestFetcher.run(repo));
        }

    }

//    private void registerVcsChangeListener(final Project project) {
//        VcsRepositoryMappingListener vcsListener = () -> {
//            final GithubRepositoryEntity repo = githubRepoFinder.run(null);
//            if (repo != null) {
//                populatePullRequests(repo);
//            }
//        };
//        project.getMessageBus().connect().subscribe(VcsRepositoryManager.VCS_REPOSITORY_MAPPING_UPDATED, vcsListener);
//    }
}
