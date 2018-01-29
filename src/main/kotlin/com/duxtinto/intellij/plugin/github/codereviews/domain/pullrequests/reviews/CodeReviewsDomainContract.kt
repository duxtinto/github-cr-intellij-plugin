package com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.reviews

import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.PullRequestEntity

interface CodeReviewsDomainContract {
    interface Repository {
        fun findAllBy(pullRequest: PullRequestEntity): List<CodeReviewEntity>
    }
}