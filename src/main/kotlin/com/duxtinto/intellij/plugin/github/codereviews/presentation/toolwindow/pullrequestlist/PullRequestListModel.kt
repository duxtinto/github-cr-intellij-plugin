package com.duxtinto.intellij.plugin.github.codereviews.presentation.toolwindow.pullrequestlist

import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.PullRequestEntity
import com.intellij.util.ui.ColumnInfo
import com.intellij.util.ui.ListTableModel

class PullRequestListModel(columns: Array<ColumnInfo<PullRequestEntity, *>>) : PullRequestList.Model {
    override val tableModel: ListTableModel<PullRequestEntity> = ListTableModel(*columns)

    override fun setPullRequests(pullRequests: List<PullRequestEntity>) {
        tableModel.items = pullRequests
    }
}
