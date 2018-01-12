package com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow;

import com.intellij.ui.content.ContentFactory;
import com.intellij.ui.content.ContentManager;

import javax.annotation.Nonnull;

import static com.google.common.base.Preconditions.checkNotNull;

public class ToolWindowContentPresenter implements ToolWindowContent.Presenter {
    @Nonnull
    private ToolWindowContent.View view;
    @Nonnull
    private final ContentFactory contentFactory;
    @Nonnull
    private final ContentManager contentManager;

    public ToolWindowContentPresenter(
            @Nonnull ContentFactory contentFactory,
            @Nonnull ContentManager contentManager) {
        this.contentFactory = checkNotNull(contentFactory);
        this.contentManager = checkNotNull(contentManager);
    }

    @Override
    public void setView(ToolWindowContent.View view) {
        this.view = checkNotNull(view);
    }

    @Override
    public void displayContent() {
        contentManager.addContent(contentFactory.createContent(view.getContent(), "", false));
    }
}
