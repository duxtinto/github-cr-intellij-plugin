package com.duxtinto.intellij.plugin.github.codereviews.helpers.random.randomizers

import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.reviews.CodeReviewEntity
import com.duxtinto.intellij.plugin.github.codereviews.helpers.random.RandomGenerator
import io.github.benas.randombeans.api.Randomizer

class CodeReviewRandomizer : Randomizer<CodeReviewEntity> {
    override fun getRandomValue(): CodeReviewEntity {
        return CodeReviewEntity(
                id = RandomGenerator.next(),
                reviewer = RandomGenerator.next(),
                state = RandomGenerator.next(),
                pull_request_id = RandomGenerator.next()
        )
    }
}
