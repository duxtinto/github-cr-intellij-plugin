package com.duxtinto.intellij.plugin.github.codereviews.presentation.pullrequestlist.columns

import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.PullRequestEntity
import com.intellij.util.ui.ColumnInfo

import javax.inject.Inject

sealed class ColumnInfoFactory {
    enum class ColumnIndexes {
        NUMBER,
        ISSUE,
        STATE,
        TITLE
    }

    abstract fun createDefaultColumns(): Array<ColumnInfo<PullRequestEntity, *>>
}

class RevieweeColumnInfoFactory
    @Inject
    constructor()
    : ColumnInfoFactory() {
    override fun createDefaultColumns(): Array<ColumnInfo<PullRequestEntity, *>> {
        return arrayOf(NumberColumnInfo(), IssuesColumnInfo(), StateColumnInfo(), TitleColumnInfo())
    }
}

class ReviewerColumnInfoFactory
    @Inject
    constructor()
    : ColumnInfoFactory() {
    override fun createDefaultColumns(): Array<ColumnInfo<PullRequestEntity, *>> {
        return arrayOf(NumberColumnInfo(), TitleColumnInfo())
    }
}
