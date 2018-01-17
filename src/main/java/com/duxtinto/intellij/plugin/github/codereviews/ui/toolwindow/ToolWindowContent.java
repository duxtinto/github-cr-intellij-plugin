package com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow;

import javax.annotation.Nonnull;
import javax.swing.*;

public interface ToolWindowContent {
    interface Model {}
    interface View {
        JComponent getContent();
    }
    interface Presenter {
        void setView(@Nonnull View view);
        void displayContent();
    }
}
