package com.duxtinto.intellij.plugin.github.codereviews.presentation.toolwindow.pullrequestlist.columns

import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.PullRequestEntity
import com.intellij.util.ui.ColumnInfo

import javax.inject.Inject

class ColumnInfoFactory
    @Inject constructor() {

    enum class ColumnIndexes {
        NUMBER,
        ISSUE,
        STATE,
        TITLE
    }

    fun createDefaultColumns(): Array<ColumnInfo<PullRequestEntity, *>> {
        return arrayOf(NumberColumnInfo(), IssuesColumnInfo(), StateColumnInfo(), TitleColumnInfo())
    }
}
