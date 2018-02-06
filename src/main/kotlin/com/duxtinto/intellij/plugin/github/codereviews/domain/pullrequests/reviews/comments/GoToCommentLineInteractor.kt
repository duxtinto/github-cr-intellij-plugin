package com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.reviews.comments

import com.duxtinto.intellij.plugin.github.codereviews.domain.DomainContract
import javax.inject.Inject

class GoToCommentLineInteractor
    @Inject
    constructor(private val editorDriver: DomainContract.EditorDriver)
    : ActionOnReviewCommentInteractor {

    override fun run(request: CodeReviewCommentEntity) {
        editorDriver.openDocument(request.filePath)?.goToLine(request.lineNumber)
    }
}