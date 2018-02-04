package com.duxtinto.intellij.plugin.github.codereviews.helpers.random.randomizers

import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.reviews.comments.CodeReviewCommentEntity
import com.duxtinto.intellij.plugin.github.codereviews.helpers.random.RandomGenerator
import io.github.benas.randombeans.api.Randomizer

class CodeReviewCommentRandomizer : Randomizer<CodeReviewCommentEntity> {
    override fun getRandomValue(): CodeReviewCommentEntity {
        return CodeReviewCommentEntity(
                id = RandomGenerator.next(),
                reviewId = RandomGenerator.next(),
                body = RandomGenerator.next(),
                filePath = RandomGenerator.next(),
                lineNumber = RandomGenerator.next()
        )
    }
}
