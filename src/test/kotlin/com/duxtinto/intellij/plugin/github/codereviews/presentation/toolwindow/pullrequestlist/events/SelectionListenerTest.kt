package com.duxtinto.intellij.plugin.github.codereviews.presentation.toolwindow.pullrequestlist.events

import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.PullRequestEntity
import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.reviews.CodeReviewsByPullRequestInteractor
import com.duxtinto.intellij.plugin.github.codereviews.helpers.fixtures.Fixture
import com.duxtinto.intellij.plugin.github.codereviews.presentation.toolwindow.codereviews.CodeReviewsPresenter
import com.intellij.ui.table.TableView
import mockit.Expectations
import mockit.FullVerifications
import mockit.Injectable
import mockit.Tested
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import javax.swing.DefaultListSelectionModel
import javax.swing.event.ListSelectionEvent

class SelectionListenerTest {
    @Test
    @DisplayName("If only one row has been selected, should trigger code reviews panel refresh")
    internal fun valueChangedHappyPath(
            @Injectable codeReviewsInteractor: CodeReviewsByPullRequestInteractor,
            @Injectable codeReviewsPresenter: CodeReviewsPresenter,
            @Injectable pullRequestTable: TableView<PullRequestEntity>,
            @Tested listener: SelectionListener) {
        // Arrange
        val event = ListSelectionEvent(DefaultListSelectionModel(), 0, 2, false)

        val pullRequest = Fixture.pullRequest().build()
        val codeReviews = Fixture.codeReview().ofPullRequest(pullRequest).buildList(5)

        object : Expectations() {
            init {
                pullRequestTable.selectedObjects
                result = listOf(pullRequest)

                pullRequestTable.selectedObject
                result = pullRequest

                codeReviewsInteractor.run(pullRequest)
                result = codeReviews
            }
        }

        // Act
        listener.valueChanged(event)

        // Assert
        object : FullVerifications(codeReviewsPresenter, codeReviewsInteractor) {
            init {
                codeReviewsPresenter.presentPullRequest(pullRequest)
                codeReviewsPresenter.presentReviews(codeReviews)
            }
        }
    }

    @Test
    @DisplayName("If the selection is still adjusting, should do nothing")
    internal fun valueChangedIsAdjusting(
            @Injectable codeReviewsInteractor: CodeReviewsByPullRequestInteractor,
            @Injectable codeReviewsPresenter: CodeReviewsPresenter,
            @Injectable pullRequestTable: TableView<PullRequestEntity>,
            @Tested listener: SelectionListener) {
        // Arrange
        val event = ListSelectionEvent(DefaultListSelectionModel(), 4, 5, true)

        // Act
        listener.valueChanged(event)

        // Assert
        object : FullVerifications() {
            init {
            }
        }
    }

    @Test
    @DisplayName("If more than a row has been selected, should do nothing")
    internal fun valueChangedForMultipleSelection(
            @Injectable codeReviewsInteractor: CodeReviewsByPullRequestInteractor,
            @Injectable codeReviewsPresenter: CodeReviewsPresenter,
            @Injectable pullRequestTable: TableView<PullRequestEntity>,
            @Tested listener: SelectionListener) {
        // Arrange
        val event = ListSelectionEvent(DefaultListSelectionModel(), 0, 2, false)

        object : Expectations() {
            init {
                pullRequestTable.selectedObjects
                result = Fixture.pullRequest().buildList(2)
            }
        }

        // Act
        listener.valueChanged(event)

        // Assert
        object : FullVerifications(codeReviewsInteractor, codeReviewsPresenter) {
            init {
            }
        }
    }

    @Test
    @DisplayName("If a row is selected, but not table has been set, should do nothing")
    internal fun valueChangedIfTableNotSet(
            @Injectable codeReviewsInteractor: CodeReviewsByPullRequestInteractor,
            @Injectable codeReviewsPresenter: CodeReviewsPresenter,
            @Tested listener: SelectionListener) {
        // Arrange
        val event = ListSelectionEvent(DefaultListSelectionModel(), 4, 5, false)

        // Act
        listener.valueChanged(event)

        // Assert
        object : FullVerifications(codeReviewsInteractor, codeReviewsPresenter) {
            init {
            }
        }
    }
}