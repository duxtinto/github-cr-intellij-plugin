package com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow.pullrequestlist.columns

import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.PullRequestEntity
import com.intellij.util.ui.ColumnInfo

import java.util.stream.Collectors

class IssuesColumnInfo : ColumnInfo<PullRequestEntity, String>("issues") {

    override fun valueOf(pullRequest: PullRequestEntity): String? {
        return pullRequest
                .closeableIssues
                .joinToString(",") {
                    (number) -> number.toString()
                }
    }
}
