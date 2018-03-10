package com.duxtinto.intellij.plugin.github.codereviews.presentation.toolwindow

import com.duxtinto.intellij.plugin.github.codereviews.helpers.fixtures.Fixture
import com.duxtinto.intellij.plugin.github.codereviews.presentation.codereviews.CodeReviewsPresenter
import com.duxtinto.intellij.plugin.github.codereviews.presentation.codereviews.CodeReviewsTreeCellRenderer
import com.duxtinto.intellij.plugin.github.codereviews.presentation.codereviews.CodeReviewsView
import com.duxtinto.intellij.plugin.github.codereviews.presentation.codereviews.formatters.CommentFormatter
import com.duxtinto.intellij.plugin.github.codereviews.presentation.pullrequestlist.PullRequestListModel
import com.duxtinto.intellij.plugin.github.codereviews.presentation.pullrequestlist.columns.RevieweeColumnInfoFactory
import com.duxtinto.intellij.plugin.github.codereviews.presentation.reviewee.pullrequests.events.MouseListener
import com.duxtinto.intellij.plugin.github.codereviews.presentation.reviewee.RevieweeContent
import com.duxtinto.intellij.plugin.github.codereviews.presentation.reviewee.RevieweeView
import com.duxtinto.intellij.plugin.github.codereviews.presentation.reviewee.pullrequests.events.SelectionListener
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import mockit.Injectable
import mockit.Mocked
import mockit.Tested
import org.assertj.swing.data.TableCell
import org.assertj.swing.edt.GuiActionRunner
import org.assertj.swing.fixture.requireCodeReviewNode
import org.assertj.swing.fixture.splitter
import org.assertj.swing.junit.testcase.AssertJSwingJUnit5TestCase
import org.assertj.swing.matcher.IdeaTableViewMatcher
import org.assertj.swing.matcher.IdeaTreeMatcher
import org.assertj.swing.matcher.SplitterMatcher
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import com.duxtinto.intellij.plugin.github.codereviews.presentation.codereviews.events.MouseListener as CodeReviewsMouseListener

internal class RevieweeViewUiTest : AssertJSwingJUnit5TestCase() {
    @Injectable
    private lateinit var toolbar: RevieweeContent.Toolbar
    @Injectable
    private lateinit var commentFormatter: CommentFormatter
    @Tested
    private lateinit var codeReviewsCellRenderer: CodeReviewsTreeCellRenderer

    private val pullRequestColumns = RevieweeColumnInfoFactory().createDefaultColumns()
    private lateinit var pullRequestListView: com.duxtinto.intellij.plugin.github.codereviews.presentation.pullrequestlist.PullRequestListView
    private lateinit var codeReviewsView: CodeReviewsView
    private lateinit var view: RevieweeView

    @Mocked
    private lateinit var codeReviewsMouseListener: CodeReviewsMouseListener

    fun initViews(
            mouseListener: MouseListener,
            selectionListener: SelectionListener) {
        view = GuiActionRunner.execute<RevieweeView> {
            pullRequestListView = com.duxtinto.intellij.plugin.github.codereviews.presentation.reviewee.pullrequests.PullRequestListView(pullRequestColumns, mouseListener, selectionListener)
            codeReviewsView = CodeReviewsView(codeReviewsMouseListener, codeReviewsCellRenderer)
            RevieweeView(pullRequestListView, codeReviewsView, toolbar)
        }
    }

    @Test
    @DisplayName("after view initialization, the pull request & code review panels should be visible")
    fun initView(
            @Mocked mouseListener: MouseListener,
            @Mocked selectionListener: SelectionListener) {
        // Arrange
        initViews(mouseListener, selectionListener)

        // Act
        val container = showContentInIdeaFrame(view.content)

        // Assert
        with(container) {
            tree(IdeaTreeMatcher())
            table(IdeaTableViewMatcher())
            splitter(SplitterMatcher())
                    .requireIsHorizontal()
                    .requireLeftComponent(pullRequestListView.content)
                    .requireRightComponent(codeReviewsView.content)
        }
    }

    @Test
    @DisplayName("after selecting a pull request, the list of code reviews has to be populated")
    fun selectPullRequest(@Mocked mouseListener: MouseListener) {
        // Arrange
        val aPullRequest = Fixture.pullRequest().build()
        val expectedReviews = Fixture.codeReview().ofPullRequest(aPullRequest).buildList(4)
        val reviewsPresenter = CodeReviewsPresenter()

        initViews(mouseListener, SelectionListener(
                mock {
                    on { run(aPullRequest) } doReturn expectedReviews
                },
                reviewsPresenter
        ))
        val container = showContentInIdeaFrame(view.content)

        val nonEmptyModel = PullRequestListModel(pullRequestColumns).apply { setPullRequests(listOf(aPullRequest)) }
        pullRequestListView.render(nonEmptyModel)
        reviewsPresenter.setView(codeReviewsView)

        // Act
        container.table().selectCell(TableCell.row(0).column(2))

        // Assert
        with(container) {
            table(IdeaTableViewMatcher())
            tree(IdeaTreeMatcher()).apply {
                for (codeReview in expectedReviews) {
                    requireCodeReviewNode(codeReview)
                }
            }
        }
    }
}