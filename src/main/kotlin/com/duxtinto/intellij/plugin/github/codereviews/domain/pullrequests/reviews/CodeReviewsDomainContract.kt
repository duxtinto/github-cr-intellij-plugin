package com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.reviews

import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.PullRequestEntity
import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.reviews.comments.CodeReviewCommentEntity

interface CodeReviewsDomainContract {
    interface Repository {
        fun findAllBy(pullRequest: PullRequestEntity): List<CodeReviewEntity>
    }

    interface Comments {
        interface Repository {
            fun getAllBy(review: CodeReviewEntity): List<CodeReviewCommentEntity>
        }

    }
}