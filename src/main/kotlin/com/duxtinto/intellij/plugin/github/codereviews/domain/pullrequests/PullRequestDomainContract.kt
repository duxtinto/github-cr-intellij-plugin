package com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests

import com.duxtinto.intellij.plugin.github.codereviews.net.pullrequests.apiv3.PullRequestQueryParameters
import java.io.IOException

interface PullRequestDomainContract {
    interface Fetcher {
        @Throws(IOException::class)
        fun fetchAllForRepository(userName: String, repoName: String, parameters: PullRequestQueryParameters): List<PullRequestEntity>
        fun fetchOneForRepository(userName: String, repoName: String, id: Long): PullRequestEntity
    }

    interface Repository {
        @Throws(IOException::class)
        fun getAllMyOpenBy(userName: String, repoName: String): List<PullRequestEntity>
        fun getOneById(userName: String, repoName: String, id: Long): PullRequestEntity?
    }

    interface DescriptionParser {
        fun parse(description: String): PullRequestDescriptionEntity
    }
}
