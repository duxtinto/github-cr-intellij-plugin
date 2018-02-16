package com.duxtinto.intellij.plugin.github.codereviews.ide.acl.services.github.codereviews

import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.PullRequestEntity
import com.duxtinto.intellij.plugin.github.codereviews.domain.repositories.GithubRepositoryEntity
import com.duxtinto.intellij.plugin.github.codereviews.ide.acl.entities.GithubConnectionExt
import com.duxtinto.intellij.plugin.github.codereviews.net.NetContract
import com.duxtinto.intellij.plugin.github.codereviews.net.codereviews.apiv3.RequestedReviewersResponse
import org.apache.http.message.BasicHeader
import org.jetbrains.plugins.github.api.GithubApiUtil
import javax.inject.Inject

class IdeaCodeReviewerLoader
    @Inject
    constructor(private val connection: GithubConnectionExt)
    : NetContract.CodeReview.Reviewer.Loader {

    companion object {
        private val V3_FULL_JSON_CONTENT = "application/vnd.github.v3.full+json"
        private val ACCEPT_HEADER = BasicHeader("Accept", V3_FULL_JSON_CONTENT)
    }

    override fun loadAllForPullRequest(
            repository: GithubRepositoryEntity,
            pullRequest: PullRequestEntity): RequestedReviewersResponse {
        return GithubApiUtil.fromJson(
                connection.delegate().getRequest(
                        "/repos/${repository.ownerName}/${repository.name}/pulls/${pullRequest.number}/requested_reviewers",
                        ACCEPT_HEADER
                ),
                RequestedReviewersResponse::class.java
        )
    }
}
