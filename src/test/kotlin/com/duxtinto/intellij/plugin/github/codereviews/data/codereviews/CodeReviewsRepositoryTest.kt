package com.duxtinto.intellij.plugin.github.codereviews.data.codereviews

import com.duxtinto.intellij.plugin.github.codereviews.helpers.fixtures.Fixture
import mockit.Expectations
import mockit.Injectable
import mockit.Tested
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class CodeReviewsRepositoryTest {
    @Injectable
    private lateinit var fetcher: CodeReviewsDataContract.Fetcher

    @Tested
    private lateinit var repository: CodeReviewsRepository

    @Test
    @DisplayName("find all by pull request should find all the reviews for the pull request")
    fun findAllByPullRequestHappyPath() {
        // Arrange
        val pullRequest = Fixture.pullRequest().build()
        val expectedCodeReviews = listOf(Fixture.codeReview().build(), Fixture.codeReview().build())

        object : Expectations() {
            init {
                fetcher.fetchAllByPullRequestId(pullRequest.number)
                result = expectedCodeReviews
            }
        }

        // Act
        val codeReviews = repository.findAllBy(pullRequest)

        // Assert
        Assertions.assertThat(expectedCodeReviews).isEqualTo(codeReviews)
    }
}