package com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow

import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.PullRequestEntity
import com.duxtinto.intellij.plugin.github.codereviews.helpers.fixtures.Fixture
import com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow.codereviews.CodeReviewsPresenter
import com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow.codereviews.CodeReviewsView
import com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow.pullrequestlist.PullRequestListModel
import com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow.pullrequestlist.PullRequestListView
import com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow.pullrequestlist.columns.ColumnInfoFactory
import com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow.pullrequestlist.events.PullRequestListMouseListener
import com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow.pullrequestlist.events.SelectionListener
import com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow.reviewee.RevieweeView
import com.intellij.util.ui.ColumnInfo
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import mockit.Mocked
import org.assertj.swing.data.TableCell
import org.assertj.swing.edt.GuiActionRunner
import org.assertj.swing.fixture.splitter
import org.assertj.swing.junit.testcase.AssertJSwingJUnit5TestCase
import org.assertj.swing.matcher.IdeaTableViewMatcher
import org.assertj.swing.matcher.IdeaTreeMatcher
import org.assertj.swing.matcher.SplitterMatcher
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class RevieweeViewUiTest : AssertJSwingJUnit5TestCase() {
    private lateinit var pullRequestColumns: Array<ColumnInfo<PullRequestEntity, *>>
    private lateinit var pullRequestListView: PullRequestListView
    private lateinit var codeReviewsView: CodeReviewsView
    private lateinit var view: RevieweeView

    fun initViewDependencies(
            mouseListener: PullRequestListMouseListener,
            selectionListener: SelectionListener) {
        pullRequestColumns = ColumnInfoFactory().createDefaultColumns()
        view = GuiActionRunner.execute<RevieweeView> {
            pullRequestListView = PullRequestListView(pullRequestColumns, mouseListener, selectionListener)
            codeReviewsView = CodeReviewsView()
            RevieweeView(pullRequestListView, codeReviewsView)
        }
    }

    @Test
    @DisplayName("after reviewee view initialization, the pull request & code review panels should be visible")
    fun initView(
            @Mocked mouseListener: PullRequestListMouseListener,
            @Mocked selectionListener: SelectionListener) {
        // Arrange
        initViewDependencies(mouseListener, selectionListener)

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
    fun selectPullRequest(
            @Mocked mouseListener: PullRequestListMouseListener) {
        // Arrange
        val aPullRequest = Fixture.pullRequest().build()
        val expectedReviews = listOf(Fixture.codeReview().build(), Fixture.codeReview().build())
        val reviewsPresenter = CodeReviewsPresenter(mock {
            on { run(aPullRequest) } doReturn expectedReviews
        })

        initViewDependencies(mouseListener, SelectionListener(reviewsPresenter))
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
                    node("Code Reviews/${codeReview.reviewer.username} [${codeReview.state}]")
                }
            }
        }
    }
}