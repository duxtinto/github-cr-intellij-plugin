package com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow.pullrequestlist.columns;

import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.PullRequestEntity;
import com.intellij.util.ui.ColumnInfo;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

class NumberColumnInfo extends ColumnInfo<PullRequestEntity, Long> {
    NumberColumnInfo() {
        super("number");
    }

    @Nullable
    @Override
    public Long valueOf(PullRequestEntity pullRequest) {
        return pullRequest.number();
    }

    @Nullable
    @Override
    public TableCellRenderer getRenderer(PullRequestEntity pullRequest) {
        return new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel cellContent = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                cellContent.setText("<html><a href=\"\">"+String.valueOf(value)+"</a></html>");
                return cellContent;
            }
        };
    }
}
