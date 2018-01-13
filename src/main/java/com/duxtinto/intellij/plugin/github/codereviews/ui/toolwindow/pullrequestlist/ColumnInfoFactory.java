package com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow.pullrequestlist;

import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.PullRequestEntity;
import com.intellij.util.ui.ColumnInfo;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class ColumnInfoFactory {
    public enum ColumnIndexes {
        NUMBER,
        STATE,
        TITLE,
    }

    public ColumnInfo[] createDefaultColumns() {
        return new ColumnInfo[]{
                new NumberColumnInfo(),
                new StateColumnInfo(),
                new TitleColumnInfo(),
        };
    }

    private class NumberColumnInfo extends ColumnInfo<PullRequestEntity, Long> {
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

    private class StateColumnInfo extends ColumnInfo<PullRequestEntity, String> {
        StateColumnInfo() {
            super("state");
        }

        @Nullable
        @Override
        public String valueOf(PullRequestEntity pullRequest) {
            return pullRequest.state().toString();
        }
    }

    private class TitleColumnInfo extends ColumnInfo<PullRequestEntity, String> {
        TitleColumnInfo() {
            super("title");
        }

        @Nullable
        @Override
        public String valueOf(PullRequestEntity pullRequest) {
            return pullRequest.title();
        }
    }
}
