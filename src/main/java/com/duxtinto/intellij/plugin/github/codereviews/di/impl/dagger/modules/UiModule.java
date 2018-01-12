package com.duxtinto.intellij.plugin.github.codereviews.di.impl.dagger.modules;

import com.duxtinto.intellij.plugin.github.codereviews.di.scopes.ProjectScoped;
import com.duxtinto.intellij.plugin.github.codereviews.ide.acl.entities.ProjectExt;
import com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow.ToolWindowContent;
import com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow.ToolWindowContentPresenter;
import com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow.ToolWindowContentView;
import com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow.pullrequestlist.ColumnInfoFactory;
import com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow.pullrequestlist.PullRequestList;
import com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow.pullrequestlist.PullRequestListPresenter;
import com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow.pullrequestlist.PullRequestListView;
import com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow.pullrequestlist.events.PullRequesListMouseInputAdapter;
import com.intellij.openapi.wm.ToolWindowManager;
import com.intellij.openapi.wm.impl.ToolWindowImpl;
import com.intellij.ui.content.ContentFactory;
import com.intellij.ui.content.ContentManager;
import dagger.Module;
import dagger.Provides;

import javax.inject.Named;

@Module
public class UiModule {
    @Provides
    @ProjectScoped
    public ToolWindowManager provideToolWindowManager(ProjectExt project) {
        return ToolWindowManager.getInstance(project.delegate());
    }

    @Provides
    @ProjectScoped
    @Named("GH_Reviews")
    public ToolWindowImpl provideToolWindowImpl(ToolWindowManager toolWindowManager) {
        return (ToolWindowImpl)toolWindowManager.getToolWindow("GitHub Reviews");
    }

    @Provides
    @ProjectScoped
    @Named("GH_Reviews")
    public ContentManager provideContentManager(@Named("GH_Reviews") ToolWindowImpl toolWindow) {
        return toolWindow.getContentManager();
    }

    @Provides
    @ProjectScoped
    public ToolWindowContent.Presenter provideToolWindowContentPresenter(
            ContentFactory contentFactory,
            @Named("GH_Reviews") ContentManager contentManager) {
        return new ToolWindowContentPresenter(contentFactory, contentManager);
    }

    @Provides
    @ProjectScoped
    public ToolWindowContent.View provideToolWindowContentView(PullRequestList.View pullRequestListView) {
        return new ToolWindowContentView(pullRequestListView);
    }

    @Provides
    @ProjectScoped
    public PullRequestList.View providePullRequestListView(
            PullRequestList.Presenter presenter,
            PullRequesListMouseInputAdapter mouseInputAdapter) {
        return new PullRequestListView(presenter, mouseInputAdapter);
    }

    @Provides
    @ProjectScoped
    public PullRequestList.Presenter providePullRequestListPresenter(ColumnInfoFactory columnInfoFactory) {
        return new PullRequestListPresenter(columnInfoFactory);
    }

    @Provides
    @ProjectScoped
    public ColumnInfoFactory provideColumnInfoFactory() {
        return new ColumnInfoFactory();
    }

    @Provides
    @ProjectScoped
    public PullRequesListMouseInputAdapter providePullRequesListMouseInputAdapter() {
        return new PullRequesListMouseInputAdapter();
    }
}
