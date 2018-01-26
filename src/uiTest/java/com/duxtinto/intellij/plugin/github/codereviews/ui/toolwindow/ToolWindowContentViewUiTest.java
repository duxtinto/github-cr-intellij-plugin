package com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow;

import com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow.pullrequestlist.PullRequestList;
import com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow.pullrequestlist.PullRequestListView;
import com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow.pullrequestlist.events.PullRequestListMouseInputAdapter;
import com.intellij.util.ui.ColumnInfo;
import mockit.Injectable;
import mockit.Tested;
import org.assertj.swing.matcher.IdeaTableViewMatcher;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.junit.testcase.AssertJSwingJUnit5TestCase;
import org.junit.jupiter.api.Test;

import static org.assertj.swing.fixture.Containers.showInFrame;

class ToolWindowContentViewUiTest extends AssertJSwingJUnit5TestCase {

    private FrameFixture frame;

    @Tested(availableDuringSetup = true)
    private PullRequestListView pullRequestListView;

    @Tested(availableDuringSetup = true)
    private ToolWindowContentView view;

    @Injectable
    ColumnInfo<?,  ?>[] columns = new ColumnInfo[0];

    @Injectable
    private PullRequestList.Presenter presenter;

    @Injectable
    private PullRequestListMouseInputAdapter mouseInputAdater;

    @Override
    protected void onSetUp() {
        frame = showInFrame(robot(), GuiActionRunner.execute(view::getContent));
        frame.show();
    }

    @Test
    void viewContainsPullRequestsTable() {
        frame.table(new IdeaTableViewMatcher());
    }
}