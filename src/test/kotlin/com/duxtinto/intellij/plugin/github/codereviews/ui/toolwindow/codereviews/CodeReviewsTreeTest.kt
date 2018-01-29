package com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow.codereviews

import com.duxtinto.intellij.plugin.github.codereviews.helpers.fixtures.Fixture
import mockit.Tested
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class CodeReviewsTreeTest {
    @Tested(fullyInitialized = true)
    private lateinit var tree: CodeReviewsTree

    @Test
    @DisplayName("convert value to text, if the node is a code review, should return the reviewer's username & the review state")
    fun convertValueToTextForCodeReview() {
        // Arrange
        val codeReview = Fixture.codeReview().build()

        // Act
        val nodeText = tree.convertValueToText(codeReview, false, false, true, 0, false)

        // Assert
        assertThat(nodeText).isEqualTo("${codeReview.reviewer.username} [${codeReview.state}]")
    }
}