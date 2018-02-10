package com.duxtinto.intellij.plugin.github.codereviews.helpers.random.randomizers.domain

import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.reviews.CodeRevieweeEntity
import com.duxtinto.intellij.plugin.github.codereviews.helpers.random.RandomGenerator
import io.github.benas.randombeans.api.Randomizer

class CodeRevieweeRandomizer : Randomizer<CodeRevieweeEntity> {
    override fun getRandomValue(): CodeRevieweeEntity {
        return CodeRevieweeEntity(
                id = RandomGenerator.next(),
                username = RandomGenerator.next()
        )
    }
}
