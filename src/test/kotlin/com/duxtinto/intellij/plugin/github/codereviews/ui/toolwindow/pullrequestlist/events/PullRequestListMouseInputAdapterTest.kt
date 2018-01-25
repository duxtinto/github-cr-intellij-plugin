package com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow.pullrequestlist.events

import com.duxtinto.intellij.plugin.github.codereviews.domain.issues.IssueEntity
import com.duxtinto.intellij.plugin.github.codereviews.domain.issues.SwitchToIssueInteractor
import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.PullRequestEntity
import com.duxtinto.intellij.plugin.github.codereviews.helpers.fixtures.Fixture
import com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow.pullrequestlist.columns.ColumnInfoFactory
import com.intellij.ide.BrowserUtil
import com.intellij.ui.table.JBTable
import com.intellij.ui.table.TableView
import mockit.*
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.EnumSource
import org.junit.jupiter.params.provider.EnumSource.Mode.EXCLUDE
import org.junit.jupiter.params.provider.MethodSource
import java.awt.event.MouseEvent
import java.net.MalformedURLException
import java.util.stream.Stream

internal class PullRequestListMouseInputAdapterTest {
    @Injectable
    lateinit var issueContextSwitcher: SwitchToIssueInteractor

    @Test
    @DisplayName("setting a null table for listening should throw NullPointerException")
    fun listenToNullTable() {
        assertThrows(NullPointerException::class.java) {
            PullRequestListMouseInputAdapter(issueContextSwitcher).listenToTable(null!!)
        }
    }

    @Test
    @DisplayName("setting a table for listening should register the listener to that table")
    fun listenToTable(@Injectable table: TableView<PullRequestEntity>) {
        val sut = PullRequestListMouseInputAdapter(issueContextSwitcher)

        sut.listenToTable(table)

        object : FullVerifications() {
            init {
                table.addMouseListener(sut)
            }
        }
    }

    @Test
    @DisplayName("setting a table for listening should unregister the listener from any previous table")
    fun listenToTableAfterAnotherTableHasBeenSet(
            @Injectable registeredTable: TableView<PullRequestEntity>,
            @Mocked newTable: TableView<PullRequestEntity>,
            @Tested mouseInputAdapter: PullRequestListMouseInputAdapter) {

        mouseInputAdapter.listenToTable(newTable)

        object : FullVerifications(newTable) {
            init {
                newTable.addMouseListener(mouseInputAdapter)
            }
        }

        object : Verifications() {
            init {
                registeredTable.removeMouseListener(mouseInputAdapter)
            }
        }
    }

    @Test
    @DisplayName("on mouse pressed, over a PR's number cell, gh's PR page should be opened in the web browser")
    fun mousePressedOverPrNumberHappyPath(
            @Injectable pullRequest: PullRequestEntity,
            @Injectable table: TableView<PullRequestEntity>,
            @Tested sut: PullRequestListMouseInputAdapter) {
        val event = MouseEvent(table, 0, 0, 0, 38, 25, 1, false, 0)

        object : Expectations() {
            init {
                table.columnAtPoint(event.point)
                result = ColumnInfoFactory.ColumnIndexes.NUMBER.ordinal
                table.rowAtPoint(event.point)
                result = 1
                table.getRow(1)
                result = pullRequest

                pullRequest.url
                result = "https://github.com/duxtinto/dummy/pull/1"
            }
        }

        object : Expectations(BrowserUtil::class.java) {
            init {
                BrowserUtil.browse("https://github.com/duxtinto/dummy/pull/1")
            }
        }

        sut.mousePressed(event)

        object : FullVerifications() {
            init {
            }
        }
    }

    @Test
    @DisplayName("on mouse pressed, if the table has not been set, should do nothing")
    fun mousePressedIfTableNotSet(
            @Injectable event: MouseEvent,
            @Tested sut: PullRequestListMouseInputAdapter) {
        sut.mousePressed(event)

        object : FullVerifications() {
            init {
            }
        }
    }

    @Test
    @DisplayName("on mouse pressed, if the table is not the event's originator, should do nothing")
    fun mousePressedIfDifferentSource(
            @Injectable table: TableView<PullRequestEntity>,
            @Tested sut: PullRequestListMouseInputAdapter) {
        val event = MouseEvent(JBTable(), 0, 0, 0, 0, 0, 1, false, 0)

        sut.mousePressed(event)

        object : FullVerifications(table) {
            init {
            }
        }
    }

    @ParameterizedTest
    @EnumSource(value = ColumnInfoFactory.ColumnIndexes::class, mode = EXCLUDE, names = ["NUMBER", "ISSUE"])
    @DisplayName("on mouse pressed, if the event is out of an actionable cell, should do nothing")
    fun mousePressedInANonActionableCell(
            columnIndex: ColumnInfoFactory.ColumnIndexes,
            @Injectable table: TableView<PullRequestEntity>,
            @Tested sut: PullRequestListMouseInputAdapter) {
        val event = MouseEvent(table, 0, 0, 0, 38, 25, 1, false, 0)

        object : Expectations() {
            init {
                table.columnAtPoint(event.point)
                result = columnIndex.ordinal
                table.rowAtPoint(event.point)
                result = 1
                table == table
                result = 0
            }
        }

        sut.mousePressed(event)

        object : FullVerifications() {
            init {
            }
        }
    }

    @ParameterizedTest
    @DisplayName("on mouse pressed, over an Issue's number cell, should trigger the first issue's context switching")
    @MethodSource("closableIssuesProvider")
    fun mousePressedOverIssueNumberHappyPath(
            closableIssues: List<IssueEntity>,
            expectedIssue: IssueEntity,
            @Injectable pullRequest: PullRequestEntity,
            @Injectable table: TableView<PullRequestEntity>,
            @Tested sut: PullRequestListMouseInputAdapter) {
        val event = MouseEvent(table, 0, 0, 0, 38, 25, 1, false, 0)

        object : Expectations() {
            init {
                table == table
                result = 0
                table.columnAtPoint(event.point)
                result = ColumnInfoFactory.ColumnIndexes.ISSUE.ordinal
                table.rowAtPoint(event.point)
                result = 1
                table.getRow(1)
                result = pullRequest

                pullRequest.closeableIssues
                result = closableIssues
                issueContextSwitcher.run(expectedIssue)
            }
        }

        sut.mousePressed(event)

        object : FullVerifications() {
            init {
            }
        }
    }

    companion object {
        @JvmStatic
        @Throws(MalformedURLException::class)
        private fun closableIssuesProvider(): Stream<Arguments> {
            val anIssue = Fixture.issue().build()
            val anotherIssue = Fixture.issue().build()

            return Stream.of(
                    Arguments.of(listOf(anIssue), anIssue),
                    Arguments.of(listOf(anIssue, anotherIssue), anIssue)
            )
        }
    }

    @Test
    @DisplayName("on mouse pressed, over an Issue's number cell of a PR that doesn't close any issue, should do nothing")
    fun mousePressedOverEmptyIssueNumberCell(
            @Injectable pullRequest: PullRequestEntity,
            @Injectable table: TableView<PullRequestEntity>,
            @Tested sut: PullRequestListMouseInputAdapter) {
        val event = MouseEvent(table, 0, 0, 0, 38, 25, 1, false, 0)

        object : Expectations() {
            init {
                table == table
                result = 0
                table.columnAtPoint(event.point)
                result = ColumnInfoFactory.ColumnIndexes.ISSUE.ordinal
                table.rowAtPoint(event.point)
                result = 1
                table.getRow(1)
                result = pullRequest

                pullRequest.closeableIssues
                result = listOf<IssueEntity>()
            }
        }

        sut.mousePressed(event)

        object : FullVerifications() {
            init {
            }
        }
    }
}