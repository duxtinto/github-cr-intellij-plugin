package com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow.codereviews

import com.duxtinto.intellij.plugin.github.codereviews.helpers.fixtures.Fixture
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import javax.swing.tree.DefaultMutableTreeNode

internal class CodeReviewsModelTest {
    @Test
    @DisplayName("on model creation, the root node and the review nodes should be created")
    internal fun constructoHappyPath() {
        // Arrange
        val reviews = listOf(Fixture.codeReview().build(), Fixture.codeReview().build())

        // Act
        val model = CodeReviewsModel(reviews)

        // Assert
        with(model.mutableRoot) {
            assertThat(userObject)
                    .isEqualTo("Code Reviews")
            assertThat(childCount)
                    .isEqualTo(2)
            assertThat((firstChild as DefaultMutableTreeNode).userObject)
                    .isSameAs(reviews.first())
            assertThat((lastChild as DefaultMutableTreeNode).userObject)
                    .isSameAs(reviews.last())
        }
    }
}