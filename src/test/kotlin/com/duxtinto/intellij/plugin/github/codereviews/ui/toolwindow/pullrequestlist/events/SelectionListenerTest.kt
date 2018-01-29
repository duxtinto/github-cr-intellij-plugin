package com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow.pullrequestlist.events

import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.PullRequestEntity
import com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow.codereviews.CodeReviews
import com.intellij.ui.table.TableView
import mockit.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import javax.swing.ListSelectionModel
import javax.swing.event.ListSelectionEvent

class SelectionListenerTest {
    @Injectable
    private lateinit var codeReviewsPresenter: CodeReviews.Presenter

    @Tested
    private lateinit var listener : SelectionListener

    @Test
    @DisplayName("If only one row has been selected, should trigger code reviews panel refresh")
    internal fun valueChangedHappyPath(
            @Mocked model: ListSelectionModel,
            @Mocked pullRequest: PullRequestEntity,
            @Mocked pullRequestTable: TableView<PullRequestEntity>) {
        // Arrange
        val event = ListSelectionEvent(model, 4, 5, false)
        listener.setTable(pullRequestTable)

        object : Expectations() {
            init {
                pullRequestTable.getRow(4)
                result = pullRequest
            }
        }

        // Act
        listener.valueChanged(event)

        // Assert
        object : FullVerifications() {
            init {
                codeReviewsPresenter.displayCodeReviews(pullRequest)
            }
        }
    }

    @Test
    @DisplayName("If the selection is still adjusting, should do nothing")
    internal fun valueChangedIsAdjusting(
            @Mocked model: ListSelectionModel,
            @Mocked pullRequestTable: TableView<PullRequestEntity>) {
        // Arrange
        val event = ListSelectionEvent(model, 4, 5, true)
        listener.setTable(pullRequestTable)

        // Act
        listener.valueChanged(event)

        // Assert
        object : FullVerifications(codeReviewsPresenter) {
            init {}
        }
    }

    @Test
    @DisplayName("If more than a row has been selected, should do nothing")
    internal fun valueChangedForMultipleSelection(
            @Mocked model: ListSelectionModel,
            @Mocked pullRequestTable: TableView<PullRequestEntity>) {
        // Arrange
        val event = ListSelectionEvent(model, 0, 2, false)
        listener.setTable(pullRequestTable)

        // Act
        listener.valueChanged(event)

        // Assert
        object : FullVerifications() {
            init {}
        }
    }

    @Test
    @DisplayName("If a row is selected, but not table has been set, should do nothing")
    internal fun valueChangedIfTableNotSet(
            @Mocked model: ListSelectionModel) {
        // Arrange
        val event = ListSelectionEvent(model, 4, 5, false)

        // Act
        listener.valueChanged(event)

        // Assert
        object : FullVerifications(codeReviewsPresenter) {
            init {}
        }
    }
}