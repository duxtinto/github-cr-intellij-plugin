package com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow.pullrequestlist.events

import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.PullRequestEntity
import com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow.codereviews.CodeReviews
import com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow.pullrequestlist.PullRequestList
import com.intellij.ui.table.TableView
import javax.inject.Inject
import javax.swing.ListSelectionModel
import javax.swing.event.ListSelectionEvent

class SelectionListener
@Inject
constructor(private val presenter: CodeReviews.Presenter)
    : PullRequestList.View.Events.SelectionListener {

    private lateinit var table: TableView<PullRequestEntity>

    override fun setTable(table: TableView<PullRequestEntity>) {
        this.table = table
    }

    override fun valueChanged(event: ListSelectionEvent) {
        if (!::table.isInitialized ||
                event.lastIndex - event.firstIndex > 1 ||
                event.valueIsAdjusting) {
            return
        }

        with(event.source as ListSelectionModel) {
            presenter.displayCodeReviews(table.getRow(event.firstIndex))
        }
    }
}