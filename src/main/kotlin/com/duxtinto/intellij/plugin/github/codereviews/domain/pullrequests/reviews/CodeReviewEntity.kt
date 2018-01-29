package com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.reviews

data class CodeReviewEntity(
    val id: Long,
    val reviewer: CodeReviewerEntity,
    val state: String = ""
)