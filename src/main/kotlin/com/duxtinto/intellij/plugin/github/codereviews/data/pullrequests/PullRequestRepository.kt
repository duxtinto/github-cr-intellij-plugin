package com.duxtinto.intellij.plugin.github.codereviews.data.pullrequests

import com.duxtinto.intellij.plugin.github.codereviews.data.DataContract
import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.PullRequestDomainContract
import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.PullRequestEntity
import com.duxtinto.intellij.plugin.github.codereviews.net.pullrequests.apiv3.PullRequestQueryParameters
import com.duxtinto.intellij.plugin.github.codereviews.net.pullrequests.apiv3.PullRequestQueryParameters.State
import javax.inject.Inject

class PullRequestRepository
    @Inject
    constructor(
            private val userFetcher: DataContract.User.Fetcher,
            private val pullRequestFetcher: PullRequestDomainContract.Fetcher,
            private val requestedReviewsFetcher: DataContract.CodeReview.Reviewer.Fetcher)
    : PullRequestDomainContract.Repository {

    override fun getOneById(userName: String, repoName: String, id: Long): PullRequestEntity? {
        return pullRequestFetcher.fetchOneForRepository(userName, repoName, id)
    }

    override fun getAllMyAssignedBy(userName: String, repoName: String): List<PullRequestEntity> {
        val parameters = PullRequestQueryParameters(state = State.OPEN)
        val authUser = userFetcher.fetchAuthenticated()
        return pullRequestFetcher
                .fetchAllForRepository(userName, repoName, parameters)
                .filter {
                    it.reviewee?.username == authUser?.username
                }
    }

    override fun getAllMyRequestedBy(userName: String, repoName: String): List<PullRequestEntity> {
        val parameters = PullRequestQueryParameters(state = State.OPEN)
        val authUser = userFetcher.fetchAuthenticated()
        return pullRequestFetcher
                .fetchAllForRepository(userName, repoName, parameters)
                .filter {
                    requestedReviewsFetcher.fetchAllByPullRequest(it).any {
                        it.username == authUser?.username
                    }
                }
    }
}
