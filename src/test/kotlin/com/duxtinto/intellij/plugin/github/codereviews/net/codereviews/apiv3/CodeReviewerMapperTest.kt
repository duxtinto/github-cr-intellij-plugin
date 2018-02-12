package com.duxtinto.intellij.plugin.github.codereviews.net.codereviews.apiv3

import com.duxtinto.intellij.plugin.github.codereviews.helpers.fixtures.Fixture
import mockit.Tested
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class CodeReviewerMapperTest {
    @Tested
    private lateinit var sut: CodeReviewerMapper

    @Test
    fun toEntity() {
        // Arrange
        val userResponse = Fixture.net().userResponse().build()

        // Act
        val reviewer = sut.toEntity(userResponse)

        // Assert
        assertThat(reviewer.id).isEqualTo(userResponse.id)
        assertThat(reviewer.username).isEqualTo(userResponse.login)
    }
}