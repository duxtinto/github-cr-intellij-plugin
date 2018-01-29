package com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow.codereviews

import com.duxtinto.intellij.plugin.github.codereviews.helpers.fixtures.Fixture
import org.assertj.swing.edt.GuiActionRunner
import org.assertj.swing.fixture.FrameFixture
import org.assertj.swing.fixture.requireEmptyText
import org.assertj.swing.junit.testcase.AssertJSwingJUnit5TestCase
import org.assertj.swing.matcher.IdeaTreeMatcher
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class CodeReviewsViewUiTest : AssertJSwingJUnit5TestCase() {

    lateinit var frame: FrameFixture

    @Test
    @DisplayName("An initialized empty setView should contain an empty message")
    fun initializeEmptyTree() {
        // Arrange
        val view = GuiActionRunner.execute<CodeReviewsView>({ CodeReviewsView() })

        // Act
        frame = showContentInIdeaFrame(view.content)

        // Assert
        frame.tree(IdeaTreeMatcher())
                .requireEmptyText("Please, select a pull request above to see here its code reviews")
    }

    @Test
    @DisplayName("Rendering a model containing reviews should display them on the tree")
    fun renderModelWithReviews() {
        // Arrange
        val view = GuiActionRunner.execute<CodeReviewsView>({ CodeReviewsView() })
        frame = showContentInIdeaFrame(view.content)

        // Act
        val expectedReviews = Fixture.codeReview().buildList(3)
        view.render(CodeReviewsModel(expectedReviews))

        // Assert
        frame.tree(IdeaTreeMatcher()).apply {
            for (codeReview in expectedReviews) {
                node("Code Reviews/${codeReview.reviewer.username} [${codeReview.state}]")
            }
        }
    }
}
