package com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.reviews

import com.duxtinto.intellij.plugin.github.codereviews.helpers.fixtures.Fixture
import mockit.Expectations
import mockit.Injectable
import mockit.Tested
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class GetAllReviewsForInteractorTest {
    @Injectable
    private lateinit var codeReviewRepository: CodeReviewsDomainContract.Repository

    @Tested
    private lateinit var interactor: GetAllReviewsForInteractor

    @Test
    @DisplayName("run, if the pull request has code reviews, they should be returned in the response")
    fun runHappyPath() {
        // Arrange
        val pullRequest = Fixture.pullRequest().build()
        val expectedCodeReviews = listOf(Fixture.codeReview().build(), Fixture.codeReview().build())

        object : Expectations() {
            init {
                codeReviewRepository.findAllBy(pullRequest)
                result = expectedCodeReviews
            }
        }

        // Act
        val codeReviews = interactor.run(pullRequest)

        // Assert
        assertThat(expectedCodeReviews).isEqualTo(codeReviews)
    }
}