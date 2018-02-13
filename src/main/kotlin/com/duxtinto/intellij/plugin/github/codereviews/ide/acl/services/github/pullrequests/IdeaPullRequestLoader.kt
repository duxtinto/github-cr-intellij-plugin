package com.duxtinto.intellij.plugin.github.codereviews.ide.acl.services.github.pullrequests

import com.duxtinto.intellij.plugin.github.codereviews.domain.DomainDataMapper
import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.PullRequestEntity
import com.duxtinto.intellij.plugin.github.codereviews.ide.acl.entities.GithubConnectionExt
import com.duxtinto.intellij.plugin.github.codereviews.net.pullrequests.apiv3.PullRequestQueryParameters
import com.duxtinto.intellij.plugin.github.codereviews.net.pullrequests.apiv3.PullRequestResponse
import org.apache.http.message.BasicHeader
import org.jetbrains.plugins.github.api.GithubApiUtil.fromJson
import org.jetbrains.plugins.github.api.GithubConnection
import java.io.IOException
import javax.inject.Inject
import com.duxtinto.intellij.plugin.github.codereviews.net.NetContract as NetContract

class IdeaPullRequestLoader
    @Inject
    constructor(
        private val connection: GithubConnectionExt,
        private val pullRequestMapper: DomainDataMapper<PullRequestEntity, PullRequestResponse>)
    : NetContract.PullRequest.Loader {

    companion object {
        private val V3_FULL_JSON_CONTENT = "application/vnd.github.v3.full+json"
        private val V3_DIFF = "application/vnd.github.v3.diff"
        private val ACCEPT_HEADER = BasicHeader("Accept", V3_FULL_JSON_CONTENT)
    }

    @Throws(IOException::class)
    override fun loadAll(userName: String, repoName: String, parameters: PullRequestQueryParameters): List<PullRequestEntity> {
        return GithubConnection.ArrayPagedRequest(
                    "/repos/$userName/$repoName/pulls?${parameters.toQueryString()}",
                    Array<PullRequestResponse>::class.java,
                    BasicHeader("Accept", V3_FULL_JSON_CONTENT))
                .getAll(connection.delegate())
                .map { pullRequestMapper.toEntity(it) }
    }

    @Throws(IOException::class)
    override fun loadOne(userName: String, repoName: String, id: Int): PullRequestEntity {
        return pullRequestMapper.toEntity(fromJson(
                connection.delegate().getRequest(
                        "/repos/$userName/$repoName/pulls/$id",
                        ACCEPT_HEADER
                ),
                PullRequestResponse::class.java
        ))
    }

}
