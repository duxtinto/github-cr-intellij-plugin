package com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow.pullrequestlist.events

import com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow.codereviews.CodeReviews
import mockit.FullVerifications
import mockit.Injectable
import mockit.Mocked
import mockit.Tested
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
    internal fun valueChangedForASingleSelection(@Mocked model: ListSelectionModel) {
        // Arrange
        val event = ListSelectionEvent(model, 0, 0, false)

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
    internal fun valueChangedForMultipleSelection(@Mocked model: ListSelectionModel) {
        // Arrange
        val event = ListSelectionEvent(model, 0, 2, false)

        // Act
        listener.valueChanged(event)

        // Assert
        object : FullVerifications() {
            init {}
        }
    }
}