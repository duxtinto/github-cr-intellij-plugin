package com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests

data class PullRequestDescriptionEntity(
    // Map<Issue number, Issue Url>
    val closableIssues: Collection<Long> = setOf()
)
