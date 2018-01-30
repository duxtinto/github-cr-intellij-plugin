package com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow.reviewee

import com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow.codereviews.CodeReviews
import com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow.pullrequestlist.PullRequestList
import mockit.Injectable
import mockit.Tested
import mockit.Verifications
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import javax.inject.Inject

internal class RevieweePresenterTest {

    @Injectable
    private lateinit var pullRequestListView: PullRequestList.View

    @Injectable
    private lateinit var pullRequestListPresenter: PullRequestList.Presenter

    @Injectable
    private lateinit var codeReviewsView: CodeReviews.View

    @Injectable
    private lateinit var codeReviewsPresenter: CodeReviews.Presenter

    @Tested
    private lateinit var presenter: RevieweePresenter

    @Test
    @DisplayName("on initialization, both the pull request & code review lists has to be set")
    fun init()
    {
        // Assert
        object : Verifications() {
            init {
                pullRequestListPresenter.setView(pullRequestListView)
                codeReviewsPresenter.setView(codeReviewsView)
            }
        }
    }
}