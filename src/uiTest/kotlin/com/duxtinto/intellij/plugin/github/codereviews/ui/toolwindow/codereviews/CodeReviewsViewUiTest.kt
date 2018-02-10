package com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow.codereviews

import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.ActionOnPullRequestInteractor
import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.reviews.comments.ActionOnReviewCommentInteractor
import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.reviews.comments.CommentsByReviewInteractor
import com.duxtinto.intellij.plugin.github.codereviews.helpers.fixtures.Fixture
import com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow.codereviews.events.MouseListener
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import mockit.Mocked
import mockit.Tested
import org.assertj.swing.edt.GuiActionRunner
import org.assertj.swing.fixture.FrameFixture
import org.assertj.swing.fixture.requireCodeReviewNode
import org.assertj.swing.fixture.requireEmptyText
import org.assertj.swing.fixture.requireReviewCommentNode
import org.assertj.swing.junit.testcase.AssertJSwingJUnit5TestCase
import org.assertj.swing.matcher.IdeaTreeMatcher
import org.assertj.swing.matcher.withName
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class CodeReviewsViewUiTest : AssertJSwingJUnit5TestCase() {

    private lateinit var frame: FrameFixture

    @Test
    @DisplayName("An initialized empty setView should contain an empty message")
    fun initializeEmptyTree(@Mocked mouseListener: MouseListener) {
        // Arrange
        val view = initializeViewDependencies(mouseListener)

        // Act
        frame = showContentInIdeaFrame(view.content)

        // Assert
        frame.tree(IdeaTreeMatcher())
                .requireEmptyText("Please, select a pull request above to see here its code reviews")
    }

    private fun initializeViewDependencies(mouseListener: MouseListener): CodeReviewsView {
        return GuiActionRunner.execute<CodeReviewsView>({
            CodeReviewsView(mouseListener, CodeReviewsTreeCellRenderer())
        })
    }

    @Test
    @DisplayName("Rendering a model containing reviews should display them on the tree")
    fun renderModelWithReviews(@Mocked mouseListener: MouseListener) {
        // Arrange
        val expectedReviews = Fixture.codeReview().buildList(3)
        val view = initializeViewDependencies(mouseListener)
        frame = showContentInIdeaFrame(view.content)

        // Act
        CodeReviewsModel().let {
            it.setReviews(expectedReviews)
            view.render(it)
        }


        // Assert
        frame.tree(IdeaTreeMatcher()).apply {
            for (codeReview in expectedReviews) {
                requireCodeReviewNode(codeReview)
            }
        }
    }

    @Test
    @DisplayName("Double clicking on a review node, should load its comments")
    fun doubleClickOnReview(
            @Mocked checkoutBranch: ActionOnPullRequestInteractor,
            @Mocked goToCommentLine: ActionOnReviewCommentInteractor,
            @Tested presenter: CodeReviewsPresenter) {
        // Arrange
        val expectedReviews = Fixture.codeReview().buildList(3)
        val expectedComments = Fixture.reviewComment().ofReview(expectedReviews[0]).buildList(5)
        val getAllCommentsFor = mock<CommentsByReviewInteractor> {
            on { run(expectedReviews[0]) } doReturn expectedComments
        }

        with(CodeReviewsPresenter()) {
            val mouseListener = MouseListener(getAllCommentsFor, this, goToCommentLine, checkoutBranch)
            val view = initializeViewDependencies(mouseListener)
            frame = showContentInIdeaFrame(view.content)
            setView(view)
            presentReviews(expectedReviews)
        }

        // Act
        with(frame.tree(withName("CodeReviews"))) {
            doubleClickRow(1)
            node(1).expand()
        }

        // Assert
        with(frame.tree(withName("CodeReviews"))) {
            for (comment in expectedComments) {
                requireReviewCommentNode(expectedReviews[0], comment)
            }
        }
    }
}
