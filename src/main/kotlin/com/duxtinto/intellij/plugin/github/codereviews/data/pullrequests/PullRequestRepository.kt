package com.duxtinto.intellij.plugin.github.codereviews.data.pullrequests

import com.duxtinto.intellij.plugin.github.codereviews.data.DataContract
import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.PullRequestDomainContract
import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.PullRequestEntity
import com.duxtinto.intellij.plugin.github.codereviews.net.pullrequests.apiv3.PullRequestQueryParameters
import com.duxtinto.intellij.plugin.github.codereviews.net.pullrequests.apiv3.PullRequestQueryParameters.State
import javax.inject.Inject
import java.io.IOException

class PullRequestRepository
    @Inject
    constructor(
            private val userFetcher: DataContract.User.Fetcher,
            private val fetcher: PullRequestDomainContract.Fetcher)
    : PullRequestDomainContract.Repository {

    override fun getOneById(userName: String, repoName: String, id: Long): PullRequestEntity? {
        return fetcher.fetchOneForRepository(userName, repoName, id)
    }

    @Throws(IOException::class)
    override fun getAllMyOpenBy(userName: String, repoName: String): List<PullRequestEntity> {
        val parameters = PullRequestQueryParameters(state = State.OPEN)
        val username = userFetcher.fetchAuthenticated()?.username
        return fetcher
                .fetchAllForRepository(userName, repoName, parameters)
                .filter {
                    it.reviewee?.username == username
                }
    }
}
