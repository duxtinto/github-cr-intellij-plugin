package com.duxtinto.intellij.plugin.github.codereviews.data.pullrequests

import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.PullRequestDomainContract
import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.PullRequestEntity
import com.duxtinto.intellij.plugin.github.codereviews.net.pullrequests.apiv3.PullRequestFetcher
import com.duxtinto.intellij.plugin.github.codereviews.net.pullrequests.apiv3.PullRequestQueryParameters
import com.duxtinto.intellij.plugin.github.codereviews.net.pullrequests.apiv3.PullRequestQueryParameters.State
import javax.inject.Inject
import java.io.IOException
import javax.inject.Named

class PullRequestRepository
    @Inject
    constructor(
            @Named("my_github_username") private val myUsername: String,
            fetcher: PullRequestDomainContract.Fetcher)
    : PullRequestDomainContract.Repository {

    private val fetcher: PullRequestFetcher = fetcher as PullRequestFetcher

    override fun getOneById(userName: String, repoName: String, id: Long): PullRequestEntity? {
        return fetcher.fetchOneForRepository(userName, repoName, id)
    }

    @Throws(IOException::class)
    override fun getAllMyOpenBy(userName: String, repoName: String): List<PullRequestEntity> {
        val parameters = PullRequestQueryParameters(state = State.OPEN)
        return fetcher
                .fetchAllForRepository(userName, repoName, parameters)
                .filter {
                    it.reviewee?.username == myUsername
                }
    }
}
