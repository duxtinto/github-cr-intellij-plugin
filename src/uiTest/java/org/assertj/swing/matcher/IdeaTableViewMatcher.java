package org.assertj.swing.matcher;

import com.intellij.ui.table.TableView;
import org.assertj.swing.core.GenericTypeMatcher;

public class IdeaTableViewMatcher extends GenericTypeMatcher<TableView> {
    public IdeaTableViewMatcher() {
        super(TableView.class);
    }

    @Override
    protected boolean isMatching(TableView component) {
        return true;
    }

    @Override
    public String toString() {
        String format = "%s[]";
        return String.format(format, getClass().getName());
    }
}
