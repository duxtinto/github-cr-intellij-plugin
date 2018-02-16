package com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Tag
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class PullRequestEntityStateTest {

    @ParameterizedTest
    @MethodSource("entityStateProvider")
    @Tag("testing")
    internal fun fromString(
            stateAsString: String,
            expectedState: PullRequestEntity.State
    ) {
        // Act
        val state = PullRequestEntity.State.fromString(stateAsString)

        // Assert
        assertThat(state).isEqualTo(expectedState)
    }

    companion object {
        @JvmStatic
        private fun entityStateProvider(): Stream<Arguments> {
            return Stream.of(
                // Open state (lowercase)
                Arguments.of(
                        "open",
                        PullRequestEntity.State.OPEN
                ),

                // Open state (uppercase)
                Arguments.of(
                        "OPEN",
                        PullRequestEntity.State.OPEN
                ),

                // Closed state (lowercase)
                Arguments.of(
                        "closed",
                        PullRequestEntity.State.CLOSED
                ),

                // Closed state (uppercase)
                Arguments.of(
                        "CLOSED",
                        PullRequestEntity.State.CLOSED
                ),

                // Empty state
                Arguments.of(
                        "",
                        PullRequestEntity.State.UNKNOWN
                ),

                // Unknown state
                Arguments.of(
                        "this state is unknown",
                        PullRequestEntity.State.UNKNOWN
                )
            )
        }
    }
}