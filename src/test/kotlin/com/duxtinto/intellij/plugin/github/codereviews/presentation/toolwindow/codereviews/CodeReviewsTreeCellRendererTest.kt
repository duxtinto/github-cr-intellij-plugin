package com.duxtinto.intellij.plugin.github.codereviews.presentation.toolwindow.codereviews

import com.duxtinto.intellij.plugin.github.codereviews.helpers.fixtures.Fixture
import com.duxtinto.intellij.plugin.github.codereviews.presentation.toolwindow.codereviews.formatters.CommentFormatter
import mockit.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import javax.swing.JTree
import javax.swing.tree.DefaultMutableTreeNode

internal class CodeReviewsTreeCellRendererTest {

    @Tested
    private lateinit var cellRenderer: CodeReviewsTreeCellRenderer

    @Test
    @DisplayName("if the cell contains a Code Review node, if should be formatted accordingly")
    fun customizeCellRendererForCodeReviewEntities(
            @Injectable commentFormatter: CommentFormatter,
            @Mocked tree: JTree) {
        // Arrange
        val review = Fixture.codeReview().build()
        val codeReviewNode = DefaultMutableTreeNode()
        codeReviewNode.userObject = review

        // Act
        cellRenderer.customizeCellRenderer(tree, codeReviewNode, false, false, true, 0, false)

        // Assert
        object : Verifications() {
            init {
                tree.convertValueToText("${review.reviewer.username} [${review.state}]", anyBoolean, anyBoolean, anyBoolean, anyInt, anyBoolean)
            }
        }
    }

    @Test
    @DisplayName("if the cell contains a Code Review Comment node, if should be formatted accordingly")
    fun customizeCellRendererForCodeReviewCommentEntities(
            @Injectable commentFormatter: CommentFormatter,
            @Mocked tree: JTree) {
        // Arrange
        val comment = Fixture.reviewComment().build()
        val codeReviewNode = DefaultMutableTreeNode()
        codeReviewNode.userObject = comment

        object : Expectations() {
            init {
                commentFormatter.format(comment)
                result = "formatted comment"
            }
        }

        // Act
        cellRenderer.customizeCellRenderer(tree, codeReviewNode, false, false, true, 0, false)

        // Assert
        object : Verifications() {
            init {
                tree.convertValueToText("formatted comment", anyBoolean, anyBoolean, anyBoolean, anyInt, anyBoolean)
            }
        }
    }
}