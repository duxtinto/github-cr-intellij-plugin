package com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow.pullrequestlist

import com.duxtinto.intellij.plugin.github.codereviews.helpers.fixtures.Fixture
import com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow.pullrequestlist.columns.*
import com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow.pullrequestlist.events.PullRequestListMouseInputAdapter
import com.intellij.util.ui.ColumnInfo
import com.nhaarman.mockitokotlin2.*
import mockit.Injectable
import mockit.Tested
import org.assertj.swing.core.MouseButton
import org.assertj.swing.data.TableCell
import org.assertj.swing.edt.GuiActionRunner
import org.assertj.swing.fixture.Containers.showInFrame
import org.assertj.swing.fixture.FrameFixture
import org.assertj.swing.fixture.pullrequest.requirePullRequestRowAt
import org.assertj.swing.junit.testcase.AssertJSwingJUnit5TestCase
import org.assertj.swing.matcher.IdeaTableViewMatcher
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import javax.swing.JComponent

class PullRequestListViewUiTest : AssertJSwingJUnit5TestCase() {

    lateinit var frame: FrameFixture

    @Injectable
    var columns = arrayOf<ColumnInfo<*, *>>(NumberColumnInfo(), IssuesColumnInfo(), StateColumnInfo(), TitleColumnInfo())

    @Test
    @DisplayName("An initialized empty table should contain the expected columns")
    fun initializeEmptyTable(
            @Injectable mouseInputAdapter: PullRequestListMouseInputAdapter,
            @Tested view: PullRequestListView) {

        showViewInFrame(view)

        val table = frame.table(IdeaTableViewMatcher())
        table
                .requireColumnCount(4)
                .requireRowCount(0)
                .requireColumnNamed("number")
                .requireColumnNamed("issues")
                .requireColumnNamed("state")
                .requireColumnNamed("title")
    }

    @Test
    @DisplayName("A table that contains a PR which closes an issue, should have a clickable issue cell")
    fun renderTableWithPullRequestWithClosableIssues() {
        // Arrange
        val pullRequest = Fixture
                .pullRequest()
                .closes(Fixture.issue().build())
                .build()
        val model = PullRequestListModel(columns)
        model.setPullRequests(arrayListOf(pullRequest))

        val mouseInputAdapter = spy(PullRequestListMouseInputAdapter(mock())) {
            doNothing().whenever(it).mousePressed(any())
        }
        val view = PullRequestListView(columns, mouseInputAdapter)

        // Act
        showViewInFrame(view)
        GuiActionRunner.execute<Unit>({ view.render(model) })

        // Assert
        frame
                .table(IdeaTableViewMatcher())
                .requireRowCount(1)
                .requirePullRequestRowAt(0, pullRequest)
                .click(TableCell.row(0).column(ColumnInfoFactory.ColumnIndexes.ISSUE.ordinal), MouseButton.LEFT_BUTTON)

        verify(mouseInputAdapter).mousePressed(any())
    }

    private fun showViewInFrame(view : PullRequestListView) {
        frame = showInFrame(robot(), GuiActionRunner.execute<JComponent>({ view.content }))
    }
}

