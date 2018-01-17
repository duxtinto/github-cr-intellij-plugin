package com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow;

import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import com.intellij.ui.content.ContentManager;
import mockit.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.assertThrows;

class ToolWindowContentPresenterTest {

    @Tested
    private ToolWindowContentPresenter presenter;

    @Injectable
    private ContentFactory contentFactory;

    @Injectable
    private ContentManager contentManager;

    @Test
    @DisplayName("setting a null view, should throw a NullPointerException")
    void setNullView() {
        assertThrows(NullPointerException.class, () -> presenter.setView(null));
    }

    @Test
    @DisplayName("displaying the content, if the view has not been set, should do nothing")
    void displayContentIfViewIsNotSet() {
        presenter.displayContent();

        new FullVerifications() {{}};
    }

    @Test
    @DisplayName("displaying the content after a view has been set, should display the view content")
    void displayContentIfViewIsSet(
            @Mocked ToolWindowContent.View view,
            @Mocked JComponent viewRootComponent,
            @Mocked Content viewContent) {
        presenter.setView(view);

        presenter.displayContent();

        new Verifications() {{
            view.getContent();
            contentFactory.createContent(viewRootComponent, anyString, anyBoolean);
            contentManager.addContent(viewContent);
        }};
    }
}