package com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow.pullrequestlist

import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.PullRequestEntity
import com.duxtinto.intellij.plugin.github.codereviews.helpers.fixtures.Fixture
import com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow.pullrequestlist.columns.*
import com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow.pullrequestlist.events.PullRequestListMouseListener
import com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow.pullrequestlist.events.SelectionListener
import com.intellij.util.ui.ColumnInfo
import com.nhaarman.mockitokotlin2.*
import mockit.Injectable
import mockit.Mocked
import mockit.Tested
import org.assertj.swing.core.MouseButton
import org.assertj.swing.data.TableCell
import org.assertj.swing.edt.GuiActionRunner
import org.assertj.swing.fixture.FrameFixture
import org.assertj.swing.fixture.pullrequest.requirePullRequestRowAt
import org.assertj.swing.junit.testcase.AssertJSwingJUnit5TestCase
import org.assertj.swing.matcher.IdeaTableViewMatcher
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class PullRequestListViewUiTest : AssertJSwingJUnit5TestCase() {

    lateinit var frame: FrameFixture

    @Injectable
    var columns = arrayOf<ColumnInfo<PullRequestEntity, *>>(NumberColumnInfo(), IssuesColumnInfo(), StateColumnInfo(), TitleColumnInfo())

    @Test
    @DisplayName("An initialized empty table should contain the expected columns")
    fun initializeEmptyTable(
            @Injectable mouseListener: PullRequestListMouseListener,
            @Injectable selectionListener: SelectionListener,
            @Tested view: PullRequestListView) {

        frame = showContentInIdeaFrame(view.content)

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
    fun renderTableWithPullRequestWithClosableIssues(@Mocked selectionListener: SelectionListener) {
        // Arrange
        val pullRequest = Fixture
                .pullRequest()
                .closes(Fixture.issue().build())
                .build()
        val model = PullRequestListModel(columns)
        model.setPullRequests(arrayListOf(pullRequest))

        val mouseListener = spy(PullRequestListMouseListener(mock())) {
            doNothing().whenever(it).mousePressed(any())
        }

        val view = PullRequestListView(columns, mouseListener, selectionListener)

        // Act
        frame = showContentInIdeaFrame(view.content)
        GuiActionRunner.execute<Unit>({ view.render(model) })

        // Assert
        frame
                .table(IdeaTableViewMatcher())
                .requireRowCount(1)
                .requirePullRequestRowAt(0, pullRequest)
                .click(TableCell.row(0).column(ColumnInfoFactory.ColumnIndexes.ISSUE.ordinal), MouseButton.LEFT_BUTTON)

        verify(mouseListener).mousePressed(any())
    }

    @Test
    @DisplayName("After selecting a PR row, the CRs related to that PR should be displayes")
    fun selectingRow(@Mocked mouseListener: PullRequestListMouseListener) {
        // Arrange
        val pullRequest = Fixture.pullRequest().build()
        val model = PullRequestListModel(columns).apply {
            setPullRequests(arrayListOf(pullRequest))
        }

        val selectionListener: SelectionListener = spy(SelectionListener()) {
            doNothing().whenever(it).valueChanged(any())
        }

        val view = PullRequestListView(columns, mouseListener, selectionListener)
        frame = showContentInIdeaFrame(view.content)
        GuiActionRunner.execute<Unit>({ view.render(model) })

        // Act
        frame.table(IdeaTableViewMatcher()).selectRows(0)

        // Assert
        verify(selectionListener).valueChanged(any())
    }
}

