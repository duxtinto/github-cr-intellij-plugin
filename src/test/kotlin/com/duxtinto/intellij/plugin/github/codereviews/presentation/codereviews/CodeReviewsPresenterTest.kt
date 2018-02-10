package com.duxtinto.intellij.plugin.github.codereviews.presentation.codereviews

import com.duxtinto.intellij.plugin.github.codereviews.helpers.fixtures.Fixture
import mockit.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class CodeReviewsPresenterTest {

    private val model = CodeReviewsModel()

    @BeforeEach
    private fun setUp() {
        object : Expectations(CodeReviewsModel::class.java) {
            init {
                CodeReviewsModel()
                result = model
            }
        }
    }

    @Test
    @DisplayName("present reviews should update the model and order the view to render it")
    internal fun presentReviewsHappyPath(
            @Injectable view: CodeReviews.View,
            @Tested presenter: CodeReviewsPresenter) {
        // Arrange
        val codeReviews = Fixture.codeReview().buildList(5)

        // Act
        presenter.presentReviews(codeReviews)

        // Assert
        object : Verifications() {
            init {
                model.setReviews(codeReviews)
                view.render(model)
            }
        }
    }

    @Test
    @DisplayName("present reviews, if view is not set, should do nothing")
    internal fun presentReviewsIfViewNotSet(@Tested presenter: CodeReviewsPresenter) {
        // Arrange
        val codeReviews = Fixture.codeReview().buildList(5)

        // Act
        presenter.presentReviews(codeReviews)

        // Assert
        object : FullVerifications() {
            init {}
        }
    }

    @Test
    @DisplayName("present comments should update the model and order the view to render it")
    internal fun presentCommentsHappyPath(
            @Injectable view: CodeReviews.View,
            @Tested presenter: CodeReviewsPresenter) {
        // Arrange
        val review = Fixture.codeReview().build()
        val comments = Fixture.reviewComment().ofReview(review).buildList(5)

        // Act
        presenter.presentComments(review, comments)

        // Assert
        object : FullVerifications() {
            init {
                model.setReviewComments(review, comments)
                view.render(model)
            }
        }
    }

    @Test
    @DisplayName("present comments, if view is not set, should do nothing")
    internal fun presentCommentsIfViewNotSet(@Tested presenter: CodeReviewsPresenter) {
        // Arrange
        val review = Fixture.codeReview().build()
        val comments = Fixture.reviewComment().ofReview(review).buildList(5)

        // Act
        presenter.presentComments(review, comments)

        // Assert
        object : FullVerifications() {
            init {}
        }
    }
}