package com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class PullRequestEntityTest {
    @Test
    fun createWithDefaults() {
        // Act
        val pullRequest = PullRequestEntity(1)

        // Assert
        assertThat(pullRequest.number).isEqualTo(1)
        assertThat(pullRequest.title).isEmpty()
        assertThat(pullRequest.state).isEqualTo(PullRequestEntity.State.OPEN)
        assertThat(pullRequest.url).isNull()
        assertThat(pullRequest.head).isNull()
        assertThat(pullRequest.reviewee).isNull()
        assertThat(pullRequest.reviewers).isEmpty()
        assertThat(pullRequest.closeableIssues).isEmpty()
    }
}