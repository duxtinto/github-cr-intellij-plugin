package com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests

import com.duxtinto.intellij.plugin.github.codereviews.domain.issues.IssueEntity
import com.google.common.base.Enums
import java.net.URL

data class PullRequestEntity (
        val number: Long,
        val closeableIssues: List<IssueEntity> = listOf(),
        val title: String = "",
        val state: State = State.OPEN,
        val url: URL? = null,
        val head: Link? = null) {
    enum class State {
        UNKNOWN,
        OPEN,
        CLOSED;
        companion object {
            fun fromString(string: String): State {
                return Enums.getIfPresent(State::class.java, string.toUpperCase()).or(State.UNKNOWN)
            }
        }
    }

    data class Link(
            val label: String,
            val ref: String,
            val sha: String
    )
}
