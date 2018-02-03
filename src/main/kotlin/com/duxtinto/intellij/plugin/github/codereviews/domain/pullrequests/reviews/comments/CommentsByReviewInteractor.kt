package com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.reviews.comments

import com.duxtinto.intellij.plugin.github.codereviews.domain.Interactor
import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.reviews.CodeReviewEntity

interface CommentsByReviewInteractor: Interactor<CodeReviewEntity, List<CodeReviewCommentEntity>>