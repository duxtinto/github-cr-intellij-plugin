package com.duxtinto.intellij.plugin.github.codereviews.di.impl.dagger.modules;

import com.duxtinto.intellij.plugin.github.codereviews.di.scopes.ProjectScoped;
import com.duxtinto.intellij.plugin.github.codereviews.ide.acl.entities.ProjectExt;
import com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow.ToolWindowContent;
import com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow.ToolWindowContentPresenter;
import com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow.ToolWindowContentView;
import com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow.pullrequestlist.PullRequestList;
import com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow.pullrequestlist.columns.ColumnInfoFactory;
import com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow.pullrequestlist.events.PullRequestListMouseInputAdapter;
import com.intellij.openapi.wm.ToolWindowManager;
import com.intellij.openapi.wm.impl.ToolWindowImpl;
import com.intellij.ui.content.ContentFactory;
import com.intellij.ui.content.ContentManager;
import com.intellij.util.ui.ColumnInfo;
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
    @Named("default")
    public ColumnInfo<?, ?>[] provideColumnInfoDefaultArray(ColumnInfoFactory columnInfoFactory) {
        return columnInfoFactory.createDefaultColumns();
    }
}
