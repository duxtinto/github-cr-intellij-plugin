package com.duxtinto.intellij.plugin.github.codereviews.data

import com.duxtinto.intellij.plugin.github.codereviews.domain.User.UserEntity
import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.PullRequestEntity
import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.reviews.CodeReviewerEntity

interface DataContract {
    interface User {
        interface Fetcher {
            fun fetchAuthenticated(): UserEntity?
        }
    }

    interface CodeReview {
        interface Reviewer {
            interface Fetcher {
                fun fetchAllByPullRequest(pullRequest: PullRequestEntity): List<CodeReviewerEntity>
            }
        }
    }
}
