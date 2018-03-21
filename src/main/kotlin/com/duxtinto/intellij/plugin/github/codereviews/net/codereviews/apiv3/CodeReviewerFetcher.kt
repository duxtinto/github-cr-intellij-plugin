package com.duxtinto.intellij.plugin.github.codereviews.net.codereviews.apiv3

import com.duxtinto.intellij.plugin.github.codereviews.data.DataContract
import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.PullRequestEntity
import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.reviews.CodeReviewerEntity
import com.duxtinto.intellij.plugin.github.codereviews.domain.repositories.FindGithubRepoForRootFolderInteractor
import com.duxtinto.intellij.plugin.github.codereviews.net.NetContract
import javax.inject.Inject

class CodeReviewerFetcher
    @Inject
    constructor(
            private val githubRootRepoFinder : FindGithubRepoForRootFolderInteractor,
            private val loader: NetContract.CodeReview.Reviewer.Loader,
            private val transformer: CodeReviewerMapper)
    : DataContract.CodeReview.Reviewer.Fetcher {
    override fun fetchAllByPullRequest(pullRequest: PullRequestEntity): List<CodeReviewerEntity> {
        return githubRootRepoFinder.run(Unit)?.let {
            loader.loadAllForPullRequest(it, pullRequest).users.map { transformer.toEntity(it) }
        } ?: listOf()
    }
}
