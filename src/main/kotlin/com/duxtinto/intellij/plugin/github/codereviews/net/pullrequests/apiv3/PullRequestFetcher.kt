package com.duxtinto.intellij.plugin.github.codereviews.net.pullrequests.apiv3

import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.PullRequestDomainContract
import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.PullRequestEntity
import com.duxtinto.intellij.plugin.github.codereviews.net.Contract
import java.io.IOException
import javax.inject.Inject

class PullRequestFetcher
    @Inject
    constructor(private val apiLoader: Contract.PullRequest.Loader)
    : PullRequestDomainContract.Fetcher {

    override fun fetchOneForRepository(userName: String, repoName: String, id: Long): PullRequestEntity {
        return apiLoader.loadOne(userName, repoName, id.toInt())
    }

    @Throws(IOException::class)
    override fun fetchAllForRepository(userName: String, repoName: String, parameters: PullRequestQueryParameters): List<PullRequestEntity> {
        return apiLoader.loadAll(userName, repoName, parameters)
    }
}
