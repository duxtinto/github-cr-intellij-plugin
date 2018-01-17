package com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow.pullrequestlist;

import com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow.pullrequestlist.columns.*;
import com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow.pullrequestlist.events.PullRequestListMouseInputAdapter;
import com.intellij.util.ui.ColumnInfo;
import mockit.Injectable;
import mockit.Tested;
import org.assertj.swing.matcher.IdeaTableViewMatcher;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.fixture.JTableFixture;
import org.assertj.swing.junit.testcase.AssertJSwingJUnit5TestCase;
import org.junit.jupiter.api.Test;

import static org.assertj.swing.fixture.Containers.showInFrame;

class PullRequestListViewUiTest extends AssertJSwingJUnit5TestCase {

    private FrameFixture frame;

    @Injectable
    ColumnInfo[] columns = new ColumnInfo[]{
            new NumberColumnInfo(),
            new IssuesColumnInfo(),
            new StateColumnInfo(),
            new TitleColumnInfo(),
    };

    @Injectable
    PullRequestListMouseInputAdapter mouseInputAdapter;

    @Tested(availableDuringSetup = true)
    private PullRequestListView view;

    @Override
    protected void onSetUp() {
        frame = showInFrame(robot(), GuiActionRunner.execute(view::getContent));
    }

    @Test
    void renderEmptyTable() {
        final JTableFixture table = frame.table(new IdeaTableViewMatcher());
        table
                .requireColumnCount(4)
                .requireRowCount(0)
                .requireColumnNamed("number")
                .requireColumnNamed("issues")
                .requireColumnNamed("state")
                .requireColumnNamed("title")
        ;

//        table.tableHeader().
    }
}