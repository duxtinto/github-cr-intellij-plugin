package com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow;

import com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow.pullrequestlist.PullRequestList;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.util.ui.JBUI;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.swing.*;
import java.awt.*;

import static com.google.common.base.Preconditions.checkNotNull;

public class ToolWindowContentView implements ToolWindowContent.View {
    @Nullable private JPanel content;

    private PullRequestList.View pullRequestListView;

    public ToolWindowContentView(@Nonnull PullRequestList.View pullRequestListView) {
        this.pullRequestListView = checkNotNull(pullRequestListView);
    }

    public JPanel getContent() {
        if (content == null) {
            content = new JPanel();
            content.setLayout(new GridLayoutManager(1, 1, JBUI.emptyInsets(), -1, -1));
            content.setAutoscrolls(false);
            content.setPreferredSize(new Dimension(-1, -1));
            content.add(pullRequestListView.getContent(), new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        }

        return content;
    }
}
