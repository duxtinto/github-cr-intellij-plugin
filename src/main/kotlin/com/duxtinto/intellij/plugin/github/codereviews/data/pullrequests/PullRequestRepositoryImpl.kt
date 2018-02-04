package com.duxtinto.intellij.plugin.github.codereviews.data.pullrequests

import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.PullRequestDomainContract
import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.PullRequestEntity
import com.duxtinto.intellij.plugin.github.codereviews.net.pullrequests.ApiV3PullRequestFetcher
import com.duxtinto.intellij.plugin.github.codereviews.net.pullrequests.PullRequestQueryParameters
import com.duxtinto.intellij.plugin.github.codereviews.net.pullrequests.PullRequestQueryParameters.State
import javax.inject.Inject
import java.io.IOException

class PullRequestRepositoryImpl @Inject
constructor(fetcher: PullRequestDomainContract.Fetcher)
    : PullRequestDomainContract.Repository {
    private val fetcher: ApiV3PullRequestFetcher = fetcher as ApiV3PullRequestFetcher

    @Throws(IOException::class)
    override fun getAllOpenBy(userName: String, repoName: String): List<PullRequestEntity> {
        val parameters = PullRequestQueryParameters(state = State.OPEN)
        return fetcher.fetchForRepository(userName, repoName, parameters)
    }
}
