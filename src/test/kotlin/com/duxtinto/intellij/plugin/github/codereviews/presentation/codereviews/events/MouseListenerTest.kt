package com.duxtinto.intellij.plugin.github.codereviews.presentation.codereviews.events

import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.ActionOnPullRequestInteractor
import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.reviews.comments.ActionOnReviewCommentInteractor
import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.reviews.comments.CommentsByReviewInteractor
import com.duxtinto.intellij.plugin.github.codereviews.helpers.fixtures.Fixture
import com.duxtinto.intellij.plugin.github.codereviews.presentation.codereviews.CodeReviews
import com.duxtinto.intellij.plugin.github.codereviews.presentation.codereviews.CodeReviewsTree
import mockit.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test
import java.awt.event.MouseEvent
import javax.swing.tree.DefaultMutableTreeNode
import javax.swing.tree.TreeModel

internal class MouseListenerTest {
    @Test
    @DisplayName("on single click, listener should do nothing")
    fun singleClick(
            @Injectable presenter: CodeReviews.Presenter,
            @Injectable getAllCommentsInteractor: CommentsByReviewInteractor,
            @Injectable goToLine: ActionOnReviewCommentInteractor,
            @Injectable checkoutBranchInteractor: ActionOnPullRequestInteractor,
            @Tested listener: MouseListener,
            @Mocked tree: CodeReviewsTree) {
        // Act
        listener.mousePressed(MouseEvent(tree, 0, 0, 0, 0, 0, 1, false))

        // Assert
        object : FullVerifications(presenter, getAllCommentsInteractor, goToLine) {
            init {}
        }
    }

    @Test
    @DisplayName("on double click, on a non-listenable node, listener should do nothing")
    fun doubleClickOnANonCodeReview(
            @Injectable presenter: CodeReviews.Presenter,
            @Injectable getAllCommentsInteractor: CommentsByReviewInteractor,
            @Injectable goToLine: ActionOnReviewCommentInteractor,
            @Injectable checkoutBranchInteractor: ActionOnPullRequestInteractor,
            @Tested listener: MouseListener,
            @Mocked tree: CodeReviewsTree) {
        // Arrange
        object : Expectations() {
            init {
                tree.lastSelectedPathComponent
                result = DefaultMutableTreeNode().apply { userObject = "this is a string object" }
            }
        }

        // Act
        listener.mousePressed(MouseEvent(tree, 0, 0, 0, 0, 0, 2, false))

        // Assert
        object : FullVerifications(presenter, getAllCommentsInteractor, goToLine) {
            init {}
        }
    }

    @Test
    @DisplayName("on double click, on a code review node, should display the list of comments")
    fun doubleClickOnACodeReviewNode(
            @Injectable presenter: CodeReviews.Presenter,
            @Injectable getAllCommentsInteractor: CommentsByReviewInteractor,
            @Injectable goToLine: ActionOnReviewCommentInteractor,
            @Injectable checkoutBranchInteractor: ActionOnPullRequestInteractor,
            @Tested listener: MouseListener,
            @Mocked tree: CodeReviewsTree) {
        // Arrange
        val codeReview = Fixture.codeReview().build()
        val reviewComments = Fixture.reviewComment().ofReview(codeReview).buildList(5)
        object : Expectations() {
            init {
                tree.lastSelectedPathComponent
                result = DefaultMutableTreeNode().apply { userObject = codeReview }

                getAllCommentsInteractor.run(codeReview)
                result = reviewComments
            }
        }

        // Act
        listener.mousePressed(MouseEvent(tree, 0, 0, 0, 0, 0, 2, false))

        // Assert
        object : FullVerifications(presenter, getAllCommentsInteractor, goToLine) {
            init {
                presenter.presentComments(codeReview, reviewComments)
            }
        }
    }

    @Test
    @DisplayName("double click on a comment node, should open the comment's file and position the caret on the proper lineNumber")
    @Tag("testing")
    fun doubleClickOnACommentNode(
            @Injectable presenter: CodeReviews.Presenter,
            @Injectable getAllCommentsInteractor: CommentsByReviewInteractor,
            @Injectable goToLine: ActionOnReviewCommentInteractor,
            @Injectable checkoutBranchInteractor: ActionOnPullRequestInteractor,
            @Tested listener: MouseListener,
            @Mocked tree: CodeReviewsTree,
            @Mocked model: TreeModel) {
        // Arrange
        val reviewComment = Fixture.reviewComment().build()
        val pullRequest = Fixture.pullRequest().build()
        val rootNode = DefaultMutableTreeNode(pullRequest)

        object : Expectations() {
            init {
                tree.lastSelectedPathComponent
                result = DefaultMutableTreeNode().apply { userObject = reviewComment }

                model.root
                result = rootNode
            }
        }

        // Act
        listener.mousePressed(MouseEvent(tree, 0, 0, 0, 0, 0, 2, false))

        // Assert
        object : FullVerifications(presenter, getAllCommentsInteractor, goToLine) {
            init {
                goToLine.run(reviewComment)
            }
        }
    }
}