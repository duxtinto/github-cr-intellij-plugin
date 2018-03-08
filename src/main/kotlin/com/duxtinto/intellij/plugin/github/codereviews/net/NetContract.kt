package com.duxtinto.intellij.plugin.github.codereviews.net

import com.duxtinto.intellij.plugin.github.codereviews.domain.user.UserEntity
import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.PullRequestEntity
import com.duxtinto.intellij.plugin.github.codereviews.domain.repositories.GithubRepositoryEntity
import com.duxtinto.intellij.plugin.github.codereviews.net.codereviews.apiv3.RequestedReviewersResponse
import com.duxtinto.intellij.plugin.github.codereviews.net.pullrequests.apiv3.PullRequestQueryParameters

interface NetContract {
    interface PullRequest {
        interface Loader {
            fun loadOne(userName: String, repoName: String, id: Int): PullRequestEntity
            fun loadAll(userName: String, repoName: String, parameters: PullRequestQueryParameters): List<PullRequestEntity>
        }
    }

    interface User {
        interface Loader {
            fun loadAuthenticated(): UserEntity
        }
    }

    interface CodeReview {
        interface Reviewer {
            interface Loader {
                fun loadAllForPullRequest(repository: GithubRepositoryEntity, pullRequest: PullRequestEntity): RequestedReviewersResponse
            }
        }
    }
}
