package com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow.codereviews

import com.duxtinto.intellij.plugin.github.codereviews.helpers.fixtures.Fixture
import mockit.Mocked
import mockit.Tested
import mockit.Verifications
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import javax.swing.JTree
import javax.swing.tree.DefaultMutableTreeNode

internal class CodeReviewsTreeCellRendererTest {

    @Tested
    private lateinit var cellRenderer: CodeReviewsTreeCellRenderer

    @Test
    @DisplayName("if the cell contains a Code Review node, if should be formatted accordingly")
    fun customizeCellRendererForCodeReviewEntities(@Mocked tree: JTree) {
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
}