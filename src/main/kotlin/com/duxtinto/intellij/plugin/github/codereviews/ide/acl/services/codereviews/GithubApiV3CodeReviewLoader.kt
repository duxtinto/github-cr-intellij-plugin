package com.duxtinto.intellij.plugin.github.codereviews.ide.acl.services.codereviews

import com.duxtinto.intellij.plugin.github.codereviews.domain.repositories.GithubRepositoryEntity
import com.duxtinto.intellij.plugin.github.codereviews.ide.acl.entities.GithubConnectionExt
import com.duxtinto.intellij.plugin.github.codereviews.net.codereviews.apiv3.CodeReviewResponse
import org.apache.http.Header
import org.apache.http.message.BasicHeader
import org.jetbrains.plugins.github.api.GithubConnection
import java.io.IOException
import javax.inject.Inject
import com.duxtinto.intellij.plugin.github.codereviews.ide.acl.services.issues.IssueDataMapper as IdeaIssueDataMapper

class GithubApiV3CodeReviewLoader
    @Inject
    constructor(private val connection : GithubConnectionExt) {

    companion object {
        val ACCEPT_V3_JSON_HTML_MARKUP = BasicHeader("Accept", "application/vnd.github.v3.html+json")
    }

    @Throws(IOException::class)
    fun loadAllForPullRequest(repository: GithubRepositoryEntity, id: Long): List<CodeReviewResponse> {
        val path = "/repos/${repository.ownerName}/${repository.name}/pulls/$id/reviews"
        return loadAll(connection, path, Array<CodeReviewResponse>::class.java, ACCEPT_V3_JSON_HTML_MARKUP)
    }

    @Throws(IOException::class)
    private fun <T> loadAll(connection: GithubConnectionExt,
                            path: String,
                            type: Class<out Array<T>>,
                            vararg headers: Header): List<T> {
        val request = GithubConnection.ArrayPagedRequest(path, type, *headers)
        return request.getAll(connection.delegate())
    }
}