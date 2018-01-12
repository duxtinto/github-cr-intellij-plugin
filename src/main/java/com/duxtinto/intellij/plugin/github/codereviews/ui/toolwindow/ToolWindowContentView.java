package com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow;

import com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow.pullrequestlist.PullRequestList;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ToolWindowContentView implements ToolWindowContent.View {
    private JPanel content;
    private PullRequestList.View pullRequestListView;
    private JButton reloadButton;

    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public ToolWindowContentView(PullRequestList.View pullRequestListView) {
        content = new JPanel();
        content.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        content.setAutoscrolls(false);
        content.setPreferredSize(new Dimension(-1, -1));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new BorderLayout(0, 0));
        content.add(panel1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        this.pullRequestListView = pullRequestListView;
        panel1.add(pullRequestListView.getContent(), BorderLayout.CENTER);
        reloadButton = new JButton();
        reloadButton.setText("Reload");
        panel1.add(reloadButton, BorderLayout.SOUTH);

    }

    public JPanel getContent() {
        return content;
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }

    public JButton getReloadButton() {
        return reloadButton;
    }
}
