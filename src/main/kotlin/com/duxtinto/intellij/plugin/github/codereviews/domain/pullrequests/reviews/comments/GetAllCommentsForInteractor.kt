package com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.reviews.comments

import com.duxtinto.intellij.plugin.github.codereviews.domain.Interactor
import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.reviews.CodeReviewEntity
import javax.inject.Inject

class GetAllCommentsForInteractor
    @Inject
    constructor()
    : Interactor<CodeReviewEntity, List<CodeReviewCommentEntity>> {
    override fun run(request: CodeReviewEntity?): List<CodeReviewCommentEntity> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}