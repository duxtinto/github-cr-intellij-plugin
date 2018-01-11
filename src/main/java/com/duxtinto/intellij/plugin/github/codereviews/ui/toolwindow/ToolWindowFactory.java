package com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow;

import com.duxtinto.intellij.plugin.github.codereviews.di.contracts.DiContainerAware;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.impl.ToolWindowImpl;
import com.intellij.ui.content.ContentFactory;
import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;
import javax.inject.Named;

public class ToolWindowFactory implements com.intellij.openapi.wm.ToolWindowFactory, DiContainerAware, DumbAware {

//    @Inject
//    FindGithubRepoForRootFolderInteractor githubRepoFinder;
//
//    @Inject
//    GetAllOpenForRepoInteractor pullRequestFetcher;

//    @Inject
//    PullRequestListPresenter presenter;

    @Inject
    ContentFactory contentFactory;

    @Inject
    @Named("GH_Reviews")
    ToolWindowImpl myToolWindow;

    @Inject
    ToolWindowContent.View view;

    @Inject
    ToolWindowContent.Presenter presenter;

    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
        getProjectContainer(project).inject(this);

        if (!myToolWindow.getId().equals(((ToolWindowImpl)toolWindow).getId())) {
            return;
        }

        presenter.displayContent();
//        presenter.displayPullRequests(new ArrayList<>());

//        view.getReloadButton().addActionListener(e -> {
//            contentManager.removeAllContents(true);
//            contentManager.addContent(contentFactory.createContent(view.getContent(), "", false));
//        });
    }

//    private Content createContent(Project project) {
//        SimpleToolWindowPanel panel = new SimpleToolWindowPanel(true, true);
//        final ActionToolbar toolbar = createToolBar();
//        toolbar.setTargetComponent(pullRequestListPanel);
//        panel.setToolbar(toolbar.getComponent());
//
//        final GithubRepositoryEntity repo = githubRepoFinder.run(null);
//        if (repo != null) {
//            populatePullRequests(repo);
//        }
//
//        registerVcsChangeListener(project);
//
//        panel.setContent(pullRequestListPanel);
//
//        return ContentFactory.SERVICE.getInstance().createContent(panel, "", false);
//    }

//    private void registerVcsChangeListener(final Project project) {
//        VcsRepositoryMappingListener vcsListener = () -> {
//            final GithubRepositoryEntity repo = githubRepoFinder.run(null);
//            if (repo != null) {
//                populatePullRequests(repo);
//            }
//        };
//        project.getMessageBus().connect().subscribe(VcsRepositoryManager.VCS_REPOSITORY_MAPPING_UPDATED, vcsListener);
//    }

//    private void populatePullRequests(GithubRepositoryEntity repo) {
//        List<GithubPullRequestExt> pullRequests = pullRequestFetcher.run(repo);
//        pullRequestListPanel.loadPullRequests(pullRequests);
//    }

//    @Nonnull
//    private ActionToolbar createToolBar() {
//        DefaultActionGroup group = new DefaultActionGroup();
//        group.add(new Separator());
//
//        final ActionToolbar actionToolbar = ActionManager.getInstance().createActionToolbar("GH_Reviews.Toolbar", group, true);
//        return actionToolbar;
//    }
}
