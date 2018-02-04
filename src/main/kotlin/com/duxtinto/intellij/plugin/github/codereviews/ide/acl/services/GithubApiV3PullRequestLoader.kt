package com.duxtinto.intellij.plugin.github.codereviews.ide.acl.services

import com.duxtinto.intellij.plugin.github.codereviews.di.scopes.ProjectScoped
import com.duxtinto.intellij.plugin.github.codereviews.domain.issues.IssueEntity
import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.PullRequestEntity
import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.issues.GetAllClosableByInteractor
import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.issues.GetAllClosableByRequest
import com.duxtinto.intellij.plugin.github.codereviews.ide.acl.entities.GithubConnectionExt
import com.duxtinto.intellij.plugin.github.codereviews.ide.acl.services.codereviews.GithubApiV3CodeReviewLoader.Companion.ACCEPT_V3_JSON_HTML_MARKUP
import org.apache.http.Header
import org.apache.http.message.BasicHeader
import org.jetbrains.plugins.github.api.GithubConnection
import org.jetbrains.plugins.github.api.data.GithubPullRequest
import javax.inject.Inject
import java.io.IOException
import java.net.MalformedURLException
import java.net.URL
import java.util.stream.Collectors

import com.google.common.base.Preconditions.checkNotNull

@ProjectScoped
class GithubApiV3PullRequestLoader
    @Inject
    constructor(
        private val connection: GithubConnectionExt,
        private val getClosableIssues: GetAllClosableByInteractor) {

    @Throws(IOException::class)
    fun loadAllPullRequests(path: String): List<PullRequestEntity> {
        return loadAll(connection, path, Array<GithubPullRequest>::class.java, ACCEPT_V3_JSON_HTML_MARKUP)
            .map {
                PullRequestEntity(
                    number = it.number,
                    closeableIssues = getClosableIssues(it.bodyHtml),
                    title = it.title,
                    state = PullRequestEntity.State.fromString(it.state),
                    url = URL(it.htmlUrl))
            }
    }

    private fun getClosableIssues(pullRequestDescriptionText: String): List<IssueEntity> {
        return getClosableIssues.run(GetAllClosableByRequest(pullRequestDescriptionText))
    }

    @Throws(IOException::class)
    private fun <T> loadAll(connection: GithubConnectionExt,
                            path: String,
                            type: Class<out Array<T>>,
                            vararg headers: Header): List<T> {
        val request = GithubConnection.ArrayPagedRequest(path, type, *headers)
        return request.getAll(connection.delegate())
    }

    companion object {
        private val ACCEPT_V3_JSON_HTML_MARKUP = BasicHeader("Accept", "application/vnd.github.v3.html+json")
    }
}
