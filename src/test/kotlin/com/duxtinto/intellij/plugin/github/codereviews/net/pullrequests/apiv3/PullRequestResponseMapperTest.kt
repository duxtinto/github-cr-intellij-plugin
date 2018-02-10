package com.duxtinto.intellij.plugin.github.codereviews.net.pullrequests.apiv3

import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.PullRequestEntity
import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.issues.GetAllClosableByInteractor
import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.reviews.CodeRevieweeEntity
import com.duxtinto.intellij.plugin.github.codereviews.helpers.fixtures.Fixture
import mockit.Expectations
import mockit.Injectable
import mockit.Mocked
import mockit.Tested
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

internal class PullRequestResponseMapperTest {

    @ParameterizedTest
    @DisplayName("mapping to entity should support all the possible pull request states")
    @MethodSource("pullRequestResponseProvider")
    fun toEntityState(
            response: PullRequestResponse,
            expectedState: PullRequestEntity.State,
            @Injectable getClosableIssues: GetAllClosableByInteractor,
            @Tested sut: PullRequestResponseMapper) {
        // Act
        val entity = sut.toEntity(response)

        // Assert
        assertThat(entity.state).isEqualTo(expectedState)
    }

    @Test
    @DisplayName("mapping to entity a complete pull request response")
    @Tag("testing")
    fun toEntityFull(
            @Mocked expectedHead: PullRequestEntity.Link,
            @Mocked expectedReviewee: CodeRevieweeEntity,
            @Injectable getClosableIssues: GetAllClosableByInteractor,
            @Tested sut: PullRequestResponseMapper) {
        // Arrange
        val assignee = Fixture.net().assignee().build()
        val response = Fixture.net().pullRequestResponse().withState("open").assignedTo(assignee).build()
        val expectedCloseableIssues = Fixture.issue().buildList(5)

        object : Expectations() {
            init {
                PullRequestEntity.Link(response.head.label, response.head.ref, response.head.sha)
                result = expectedHead

                CodeRevieweeEntity(assignee.id, assignee.login)
                result = expectedReviewee

                getClosableIssues.run(response.bodyHtml)
                result = expectedCloseableIssues
            }
        }

        // Act
        val entity = sut.toEntity(response)

        // Assert
        assertThat(entity.number).isEqualTo(response.number)
        assertThat(entity.state).isEqualTo(PullRequestEntity.State.OPEN)
        assertThat(entity.title).isEqualTo(response.title)
        assertThat(entity.closeableIssues).isEqualTo(expectedCloseableIssues)
        assertThat(entity.url.toString()).isEqualTo(response.htmlUrl)
        assertThat(entity.head).isEqualTo(expectedHead)
        assertThat(entity.reviewee).isEqualTo(expectedReviewee)
    }

    companion object {
        @JvmStatic
        private fun pullRequestResponseProvider(): Stream<Arguments> {
            val aResponse = Fixture.net().pullRequestResponse().build()

            return Stream.of(
                    // Open pull request
                    Arguments.of(
                            aResponse.copy(state = "open"),
                            PullRequestEntity.State.OPEN
                    ),

                    // Close pull request
                    Arguments.of(
                            aResponse.copy(state = "closed"),
                            PullRequestEntity.State.CLOSED
                    ),

                    // pull request in an unknown state
                    Arguments.of(
                            aResponse.copy(state = "state-is-unknown-by-the-plugin"),
                            PullRequestEntity.State.UNKNOWN
                    )
            )
        }
    }
}
