package com.duxtinto.intellij.plugin.github.codereviews.net.pullrequests

import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.PullRequestDomainContract
import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.PullRequestEntity
import com.duxtinto.intellij.plugin.github.codereviews.ide.acl.services.github.pullrequests.GithubApiV3PullRequestLoader
import javax.inject.Inject
import java.io.IOException

class ApiV3PullRequestFetcher @Inject
constructor(private val apiLoader: GithubApiV3PullRequestLoader) : PullRequestDomainContract.Fetcher {

    @Throws(IOException::class)
    override fun fetchForRepository(userName: String, repoName: String, parameters: PullRequestQueryParameters): List<PullRequestEntity> {
        val path = "/repos/" + userName + "/" + repoName + "/pulls?" + parameters.toQueryString()
        return apiLoader.loadAllPullRequests(path)
    }
}
