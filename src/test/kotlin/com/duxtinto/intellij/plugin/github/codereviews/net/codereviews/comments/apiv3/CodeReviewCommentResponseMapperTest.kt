package com.duxtinto.intellij.plugin.github.codereviews.net.codereviews.comments.apiv3

import com.duxtinto.intellij.plugin.github.codereviews.helpers.fixtures.Fixture
import mockit.Tested
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class CodeReviewCommentResponseMapperTest {

    @Test
    @DisplayName("mapping an active comment")
    fun toEntityActiveComment(@Tested sut: CodeReviewCommentResponseMapper) {
        // Arrange
        val apiResponse = Fixture.net().commentResponse().withPosition(10).build()

        // Act
        val comment = sut.toEntity(apiResponse)

        // Assert
        assertThat(comment.isOutdated()).isFalse()
        assertThat(comment.lineNumber).isEqualTo(10)
    }

    @Test
    @DisplayName("mapping an outdated comment")
    fun toEntityOutdatedComment(@Tested sut: CodeReviewCommentResponseMapper) {
        // Arrange
        val apiResponse = Fixture.net().commentResponse().withPosition(null).build()

        // Act
        val comment = sut.toEntity(apiResponse)

        // Assert
        assertThat(comment.isOutdated()).isTrue()
        assertThat(comment.lineNumber).isEqualTo(0)
    }
}
