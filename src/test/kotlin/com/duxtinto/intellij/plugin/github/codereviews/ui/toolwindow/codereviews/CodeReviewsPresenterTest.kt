package com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow.codereviews

import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.reviews.CodeReviewEntity
import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.reviews.GetAllReviewsForInteractor
import com.duxtinto.intellij.plugin.github.codereviews.helpers.fixtures.Fixture
import mockit.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class CodeReviewsPresenterTest {
    @Injectable
    private lateinit var getAllCodeReviewsFor: GetAllReviewsForInteractor

    @Tested
    private lateinit var presenter: CodeReviewsPresenter

    @Test
    @DisplayName("display code reviews should create model and order the view to render it")
    internal fun displayCodeReviewsHappyPath(
            @Mocked model: CodeReviewsModel,
            @Mocked view: CodeReviews.View) {
        // Arrange
        val pullRequest = Fixture.pullRequest().build()
        presenter.setView(view)

        object : Expectations() {
            init {
                val codeReviews = listOf<CodeReviewEntity>()

                getAllCodeReviewsFor.run(pullRequest)
                result = codeReviews

                CodeReviewsModel(codeReviews)
                result = model
            }
        }

        // Act
        presenter.displayCodeReviews(pullRequest)

        // Assert
        object : Verifications() {
            init {
                view.render(model)
            }
        }
    }

    @Test
    @DisplayName("display code reviews, if view is not set, should do nothing")
    internal fun displayCodeReviewsIfViewNotSet() {
        // Arrange
        val pullRequest = Fixture.pullRequest().build()

        // Act
        presenter.displayCodeReviews(pullRequest)

        // Assert
        object : FullVerifications() {
            init {}
        }
    }
}