package com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests

import com.duxtinto.intellij.plugin.github.codereviews.net.pullrequests.apiv3.PullRequestQueryParameters

interface PullRequestDomainContract {
    interface Fetcher {
        fun fetchAllForRepository(userName: String, repoName: String, parameters: PullRequestQueryParameters): List<PullRequestEntity>
        fun fetchOneForRepository(userName: String, repoName: String, id: Long): PullRequestEntity
    }

    interface Repository {
        fun getAllMyAssignedBy(userName: String, repoName: String): List<PullRequestEntity>
        fun getOneById(userName: String, repoName: String, id: Long): PullRequestEntity?
        fun getAllMyRequestedBy(userName: String, repoName: String): List<PullRequestEntity>
    }

    interface DescriptionParser {
        fun parse(description: String): PullRequestDescriptionEntity
    }
}
