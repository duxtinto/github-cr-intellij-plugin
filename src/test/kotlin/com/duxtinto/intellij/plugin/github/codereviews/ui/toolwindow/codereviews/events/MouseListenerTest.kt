package com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow.codereviews.events

import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.reviews.comments.CommentsByReviewInteractor
import com.duxtinto.intellij.plugin.github.codereviews.helpers.fixtures.Fixture
import com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow.codereviews.CodeReviews
import com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow.codereviews.CodeReviewsTree
import mockit.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.awt.event.MouseEvent
import javax.swing.tree.DefaultMutableTreeNode

internal class MouseListenerTest {
    @Injectable
    private lateinit var presenter: CodeReviews.Presenter

    @Injectable
    private lateinit var getAllCommentsInteractor: CommentsByReviewInteractor

    @Tested
    private lateinit var listener: MouseListener

    @Test
    @DisplayName("on single click, listener should do nothing")
    fun singleClick(@Mocked event: MouseEvent) {
        // Arrange
        object : Expectations() {
            init {
                event.clickCount
                result = 1
            }
        }

        // Act
        listener.mousePressed(event)

        // Assert
        object : FullVerifications() {
            init {}
        }
    }

    @Test
    @DisplayName("on double click, on a non code review node, listener should do nothing")
    fun doubleClickOnANonCodeReview(
            @Mocked tree: CodeReviewsTree,
            @Mocked event: MouseEvent) {
        // Arrange
        object : Expectations() {
            init {
                event.clickCount
                result = 2

                event.source
                result = tree

                tree.lastSelectedPathComponent
                result = DefaultMutableTreeNode().apply { userObject = "this is a string object" }
            }
        }

        // Act
        listener.mousePressed(event)

        // Assert
        object : FullVerifications() {
            init {}
        }
    }

    @Test
    @DisplayName("on double click, on a code review node, should display the list of comments")
    fun doubleClickOnACodeReviewNode(
            @Mocked tree: CodeReviewsTree,
            @Mocked event: MouseEvent) {
        // Arrange
        val codeReview = Fixture.codeReview().build()
        val reviewComments = Fixture.reviewComments().ofReview(codeReview).buildList(5)
        object : Expectations() {
            init {
                event.clickCount
                result = 2

                event.source
                result = tree

                tree.lastSelectedPathComponent
                result = DefaultMutableTreeNode().apply { userObject = codeReview }

                getAllCommentsInteractor.run(codeReview)
                result = reviewComments
            }
        }

        // Act
        listener.mousePressed(event)

        // Assert
        object : FullVerifications() {
            init {
                presenter.presentComments(codeReview, reviewComments)
            }
        }
    }
}