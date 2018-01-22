package com.duxtinto.intellij.plugin.github.codereviews.domain.issues

data class IssueEntity (
    val number: Long,
    val title: String = ""
)
