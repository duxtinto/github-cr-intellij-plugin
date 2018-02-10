package com.duxtinto.intellij.plugin.github.codereviews.ide.acl.services.github.issues

import com.duxtinto.intellij.plugin.github.codereviews.helpers.fixtures.Fixture
import mockit.Tested
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class IssueDataMapperTest {

    @Tested
    lateinit var dataMapper: IssueDataMapper

    @Test
    @DisplayName("to entity should map an ide github isuue")
    fun toEntityHappyPath() {
        // Arrange
        val ideIssue = Fixture.ide().issue()
                .withNumber(6L)
                .withTitle("my new issue")
                .build()

        // Act
        val issue = dataMapper.toEntity(ideIssue)

        // Assert
        assertThat(issue)
                .extracting("number", "title")
                .contains(6L, "my new issue")
    }
}