package com.duxtinto.intellij.plugin.github.codereviews.presentation.reviewer.pullrequests.events

import com.duxtinto.intellij.plugin.github.codereviews.domain.issues.SwitchToIssueInteractor
import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.ActionOnPullRequestInteractor
import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.PullRequestEntity
import com.duxtinto.intellij.plugin.github.codereviews.presentation.pullrequestlist.PullRequestList
import com.duxtinto.intellij.plugin.github.codereviews.presentation.pullrequestlist.columns.ColumnInfoFactory.ColumnNames
import com.intellij.ide.BrowserUtil
import com.intellij.ui.table.TableView
import java.awt.event.MouseEvent
import javax.inject.Inject
import javax.inject.Named
import javax.swing.event.MouseInputAdapter

class MouseListener
    @Inject
    constructor(
            private val issueContextSwitcher: SwitchToIssueInteractor,
            @Named("pullBranch") private val pullBranchInteractor: ActionOnPullRequestInteractor,
            @Named("checkoutBranch") private val checkoutBranchInteractor: ActionOnPullRequestInteractor)
    : MouseInputAdapter(), PullRequestList.View.Events.MouseListener {

    override fun mousePressed(event: MouseEvent) {
        if (event.source is TableView<*>) {
            with(event.source as TableView<PullRequestEntity>) {
                val rowIndex = rowAtPoint(event.point)
                if (rowIndex != -1) {
                    val pullRequest = getRow(rowIndex)
                    when (getColumnName(columnAtPoint(event.point))) {
                        ColumnNames.NUMBER.value -> {
                            BrowserUtil.browse(pullRequest.url!!)
                        }
                        ColumnNames.TITLE.value -> {
                            pullBranchInteractor.run(pullRequest)
                            checkoutBranchInteractor.run(pullRequest)
                        }
                    }
                }
            }
        }
    }
}

