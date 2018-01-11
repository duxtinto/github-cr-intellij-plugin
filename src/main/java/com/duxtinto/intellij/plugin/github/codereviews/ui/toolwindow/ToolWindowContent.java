package com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow;

import javax.swing.*;

public interface ToolWindowContent {
    interface Model {}
    interface View {
        JComponent getContent();
    }
    interface Presenter {
        void displayContent();
    }
}
