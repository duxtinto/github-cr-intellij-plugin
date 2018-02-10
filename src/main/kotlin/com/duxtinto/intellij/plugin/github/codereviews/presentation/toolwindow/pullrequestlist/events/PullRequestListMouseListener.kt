package com.duxtinto.intellij.plugin.github.codereviews.presentation.toolwindow.pullrequestlist.events

import com.duxtinto.intellij.plugin.github.codereviews.di.scopes.ProjectScoped
import com.duxtinto.intellij.plugin.github.codereviews.domain.issues.SwitchToIssueInteractor
import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.PullRequestEntity
import com.duxtinto.intellij.plugin.github.codereviews.presentation.toolwindow.pullrequestlist.columns.ColumnInfoFactory.ColumnIndexes.ISSUE
import com.duxtinto.intellij.plugin.github.codereviews.presentation.toolwindow.pullrequestlist.columns.ColumnInfoFactory.ColumnIndexes.NUMBER
import com.intellij.ide.BrowserUtil
import com.intellij.ui.table.TableView
import java.awt.event.MouseEvent
import javax.inject.Inject
import javax.swing.event.MouseInputAdapter

@ProjectScoped
class PullRequestListMouseListener
    @Inject
    constructor(private val issueContextSwitcher: SwitchToIssueInteractor)
    : MouseInputAdapter() {

    override fun mousePressed(event: MouseEvent) {
        if (event.source is TableView<*>) {
            with(event.source as TableView<PullRequestEntity>) {
                val rowIndex = rowAtPoint(event.point)
                if (rowIndex != -1) {
                    when (columnAtPoint(event.point)) {
                        NUMBER.ordinal -> BrowserUtil.browse(getRow(rowIndex).url!!)
                        ISSUE.ordinal -> {
                            getRow(rowIndex).closeableIssues.apply {
                                if (!isEmpty()) {
                                    issueContextSwitcher.run(this[0])
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
