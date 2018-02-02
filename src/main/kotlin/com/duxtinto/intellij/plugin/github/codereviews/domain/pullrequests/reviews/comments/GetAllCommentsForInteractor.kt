package com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.reviews.comments

import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.reviews.CodeReviewEntity
import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.reviews.CodeReviewsDomainContract
import javax.inject.Inject

class GetAllCommentsForInteractor
    @Inject
    constructor(private val commentsRepository: CodeReviewsDomainContract.Comments.Repository)
    : CommentsByReviewInteractor {

    override fun run(request: CodeReviewEntity): List<CodeReviewCommentEntity> {
        return commentsRepository.getAllBy(request)
    }
}