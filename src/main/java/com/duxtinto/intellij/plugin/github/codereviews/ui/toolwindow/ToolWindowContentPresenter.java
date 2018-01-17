package com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow;

import com.intellij.ui.content.ContentFactory;
import com.intellij.ui.content.ContentManager;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static com.google.common.base.Preconditions.checkNotNull;

public class ToolWindowContentPresenter implements ToolWindowContent.Presenter {
    @Nullable
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
    public void setView(@Nonnull ToolWindowContent.View view) {
        this.view = checkNotNull(view);
    }

    @Override
    public void displayContent() {
        if (view != null) {
            contentManager.addContent(contentFactory.createContent(view.getContent(), "", false));
        }
    }
}
