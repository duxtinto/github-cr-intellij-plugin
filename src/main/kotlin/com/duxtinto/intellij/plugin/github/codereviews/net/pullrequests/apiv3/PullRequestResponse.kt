package com.duxtinto.intellij.plugin.github.codereviews.net.pullrequests.apiv3

data class PullRequestResponse(
        val number: Long,
        val state: String,
        val title: String,
        val body: String,
        val bodyHtml: String = "",
        val bodyText: String = "",
        val htmlUrl: String,
        val head: Link,
        val assignee: Assignee? = null
) {
    data class Link(
            val label: String,
            val ref: String,
            val sha: String
    )

    data class Assignee(
            val id: Long,
            val login: String
    )
}
