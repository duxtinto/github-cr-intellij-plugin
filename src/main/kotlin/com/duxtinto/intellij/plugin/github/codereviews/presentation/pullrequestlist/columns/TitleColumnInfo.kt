package com.duxtinto.intellij.plugin.github.codereviews.presentation.pullrequestlist.columns

import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.PullRequestEntity
import com.intellij.util.ui.ColumnInfo

class TitleColumnInfo : ColumnInfo<PullRequestEntity, String>(ColumnInfoFactory.ColumnNames.TITLE.value) {

    override fun valueOf(pullRequest: PullRequestEntity): String? {
        return pullRequest.title
    }
}
