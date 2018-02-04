package com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests

import com.duxtinto.intellij.plugin.github.codereviews.net.pullrequests.PullRequestQueryParameters
import java.io.IOException

interface PullRequestDomainContract {
    interface Fetcher {
        @Throws(IOException::class)
        fun fetchForRepository(userName: String, repoName: String, parameters: PullRequestQueryParameters): List<PullRequestEntity>
    }

    interface Repository {
        @Throws(IOException::class)
        fun getAllOpenBy(userName: String, repoName: String): List<PullRequestEntity>
    }

    interface DescriptionParser {
        fun parse(description: String): PullRequestDescriptionEntity
    }
}
