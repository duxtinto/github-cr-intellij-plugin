package com.duxtinto.intellij.plugin.github.codereviews.presentation.codereviews.formatters

import com.duxtinto.intellij.plugin.github.codereviews.helpers.fixtures.Fixture
import mockit.Tested
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class CommentFormatterTest {

    @Test
    @DisplayName("formatting a non outdated comment, should just return the comment body")
    fun formatNonOutdated(@Tested sut: CommentFormatter) {
        // Arrange
        val comment = Fixture.reviewComment().build()

        // Act
        val formattedComment = sut.format(comment)

        // Assert
        assertThat(formattedComment).isEqualTo(comment.body)
    }

    @Test
    @DisplayName("formatting an outdated comment, should indicate it")
    fun formatOutdated(@Tested sut: CommentFormatter) {
        // Arrange
        val comment = Fixture.reviewComment().isOutdated().build()

        // Act
        val formattedComment = sut.format(comment)

        // Assert
        assertThat(formattedComment).isEqualTo("[OUTDATED] ${comment.body}")
    }
}