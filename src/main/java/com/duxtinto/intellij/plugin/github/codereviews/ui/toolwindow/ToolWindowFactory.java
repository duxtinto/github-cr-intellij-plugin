package com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow;

import com.duxtinto.intellij.plugin.github.codereviews.di.contracts.DiContainerAware;
import com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow.views.PullRequestListPanel;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.impl.ToolWindowImpl;
import com.intellij.ui.content.ContentFactory;
import com.intellij.ui.content.ContentManager;
import org.jetbrains.annotations.NotNull;

public class ToolWindowFactory implements com.intellij.openapi.wm.ToolWindowFactory, DiContainerAware, DumbAware {

//    @Inject
//    FindGithubRepoForRootFolderInteractor githubRepoFinder;
//
//    @Inject
//    GetAllOpenForRepoInteractor pullRequestFetcher;

    private ToolWindowImpl myToolWindow;
    private ContentManager contentManager;
    private ContentFactory contentFactory;
    private final PullRequestListPanel pullRequestPanel = new PullRequestListPanel();

    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {

        myToolWindow = (ToolWindowImpl)toolWindow;
        contentManager = myToolWindow.getContentManager();
        contentFactory = ContentFactory.SERVICE.getInstance();

        getProjectContainer(project).inject(this);
        contentManager.addContent(contentFactory.createContent(pullRequestPanel.getContent(), "", false));

//        pullRequestPanel.getReloadButton().addActionListener(e -> {
//            contentManager.removeAllContents(true);
//            contentManager.addContent(contentFactory.createContent(pullRequestPanel.getContent(), "", false));
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
