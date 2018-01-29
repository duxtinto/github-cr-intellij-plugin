package com.duxtinto.intellij.plugin.github.codereviews.data.codereviews

import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.reviews.CodeReviewEntity

interface CodeReviewsDataContract {
    interface Fetcher {
        fun fetchAllByPullRequestId(id: Long): List<CodeReviewEntity>
    }
}