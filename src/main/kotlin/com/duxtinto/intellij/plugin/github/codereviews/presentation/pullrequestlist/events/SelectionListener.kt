package com.duxtinto.intellij.plugin.github.codereviews.presentation.pullrequestlist.events

import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.PullRequestEntity
import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.reviews.CodeReviewsByPullRequestInteractor
import com.duxtinto.intellij.plugin.github.codereviews.presentation.codereviews.CodeReviews
import com.duxtinto.intellij.plugin.github.codereviews.presentation.pullrequestlist.PullRequestList
import com.intellij.ui.table.TableView
import javax.inject.Inject
import javax.swing.event.ListSelectionEvent

class SelectionListener
@Inject
constructor(
        private val getAllCodeReviewsFor: CodeReviewsByPullRequestInteractor,
        private val presenter: CodeReviews.Presenter)
    : PullRequestList.View.Events.SelectionListener {

    private var table: TableView<PullRequestEntity>? = null

    override fun setTable(table: TableView<PullRequestEntity>) {
        this.table = table
    }

    override fun valueChanged(event: ListSelectionEvent) {
        if ((table == null) ||
            event.valueIsAdjusting ||
            (table!!.selectedObjects.size != 1)) {
            return
        }

        val pullRequest = table!!.selectedObject as PullRequestEntity

        presenter.presentPullRequest(pullRequest)
        presenter.presentReviews(getAllCodeReviewsFor.run(pullRequest)!!)
    }
}