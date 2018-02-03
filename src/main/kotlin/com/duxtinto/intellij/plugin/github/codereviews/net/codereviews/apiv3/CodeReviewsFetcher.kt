package com.duxtinto.intellij.plugin.github.codereviews.net.codereviews.apiv3

import com.duxtinto.intellij.plugin.github.codereviews.data.codereviews.CodeReviewsDataContract
import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.reviews.CodeReviewEntity
import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.reviews.comments.CodeReviewCommentEntity
import com.duxtinto.intellij.plugin.github.codereviews.domain.repositories.FindGithubRepoForRootFolderInteractor
import com.duxtinto.intellij.plugin.github.codereviews.ide.acl.services.codereviews.GithubApiV3CodeReviewLoader
import com.duxtinto.intellij.plugin.github.codereviews.net.codereviews.comments.apiv3.CodeReviewCommentResponseMapper
import javax.inject.Inject

class CodeReviewsFetcher
    @Inject
    constructor(
            private val githubRootRepoFinder : FindGithubRepoForRootFolderInteractor,
            private val loader: GithubApiV3CodeReviewLoader,
            private val transformer: CodeReviewResponseMapper,
            private val commentTransformer: CodeReviewCommentResponseMapper)
    : CodeReviewsDataContract.Fetcher {

    override fun fetchAllByPullRequestId(id: Long): List<CodeReviewEntity> {
        return githubRootRepoFinder.run(null)?.let {
            loader.loadAllForPullRequest(it, id).map { transformer.toEntity(it) }
        } ?: listOf()
    }

    override fun fetchAllReviewComments(review: CodeReviewEntity): List<CodeReviewCommentEntity> {
        return githubRootRepoFinder.run(null)?.let {
            loader.loadAllReviewComments(it, review).map { commentTransformer.toEntity(it) }
        } ?: listOf()
    }
}