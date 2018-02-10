package com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.reviews.comments

data class CodeReviewCommentEntity (
        val id: Long,
        val reviewId: Long,
        val body: String = "",
        val filePath: String = "",
        val lineNumber: Int = 0,
        val state: State = State.ACTIVE
) {
    enum class State {
        ACTIVE,
        OUTDATED
    }

    fun isOutdated(): Boolean {
        return state == State.OUTDATED
    }
}