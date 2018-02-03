package com.duxtinto.intellij.plugin.github.codereviews.data.codereviews.comments

import com.duxtinto.intellij.plugin.github.codereviews.data.codereviews.CodeReviewsDataContract
import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.reviews.CodeReviewEntity
import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.reviews.CodeReviewsDomainContract
import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.reviews.comments.CodeReviewCommentEntity
import javax.inject.Inject

class CommentsRepository
    @Inject
    constructor(private val fetcher: CodeReviewsDataContract.Fetcher)
    : CodeReviewsDomainContract.Comments.Repository {

    override fun getAllBy(review: CodeReviewEntity): List<CodeReviewCommentEntity> {
        return fetcher.fetchAllReviewComments(review)
    }
}