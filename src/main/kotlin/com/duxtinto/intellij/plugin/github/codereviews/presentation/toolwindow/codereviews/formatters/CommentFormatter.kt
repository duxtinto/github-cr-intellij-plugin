package com.duxtinto.intellij.plugin.github.codereviews.presentation.toolwindow.codereviews.formatters

import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.reviews.comments.CodeReviewCommentEntity
import com.duxtinto.intellij.plugin.github.codereviews.presentation.Formatter
import javax.inject.Inject

class CommentFormatter
    @Inject
    constructor()
    : Formatter<CodeReviewCommentEntity, String> {
    override fun format(source: CodeReviewCommentEntity): String {
        return if (source.isOutdated()) { "[OUTDATED] ${source.body}" } else { source.body }
    }
}