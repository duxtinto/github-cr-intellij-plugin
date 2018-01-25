package com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow

import com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow.pullrequestdetails.PullRequestDetailsView
import com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow.pullrequestlist.PullRequestListView
import com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow.pullrequestlist.events.PullRequestListMouseInputAdapter
import mockit.Mocked
import org.assertj.swing.edt.GuiActionRunner
import org.assertj.swing.fixture.FrameFixture
import org.assertj.swing.fixture.splitter
import org.assertj.swing.junit.testcase.AssertJSwingJUnit5TestCase
import org.assertj.swing.matcher.IdeaTableViewMatcher
import org.assertj.swing.matcher.IdeaTreeMatcher
import org.assertj.swing.matcher.SplitterMatcher
import org.junit.jupiter.api.Test

internal class ToolWindowContentViewUiTest : AssertJSwingJUnit5TestCase() {

    private lateinit var container: FrameFixture

    private lateinit var pullRequestListView: PullRequestListView

    private lateinit var pullRequestDetailsView: PullRequestDetailsView

    private lateinit var view: ToolWindowContentView

    @Mocked
    private lateinit var mouseInputAdapter: PullRequestListMouseInputAdapter

    override fun onSetUp() {
        view = GuiActionRunner.execute<ToolWindowContentView> {
            pullRequestListView = PullRequestListView(arrayOfNulls(0), mouseInputAdapter)
            pullRequestDetailsView = PullRequestDetailsView()
            ToolWindowContentView(pullRequestListView, pullRequestDetailsView)
        }
    }

    @Test
    fun showView() {
        // Act
        container = showContentInIdeaFrame(view.content)

        // Assert
        with(container) {
            tree(IdeaTreeMatcher())
            table(IdeaTableViewMatcher())
            splitter(SplitterMatcher())
                    .requireIsHorizontal()
                    .requireLeftComponent(pullRequestListView.content)
                    .requireRightComponent(pullRequestDetailsView.content)
        }
    }
}