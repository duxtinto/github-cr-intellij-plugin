package com.duxtinto.intellij.plugin.github.codereviews.helpers.random.randomizers

import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.reviews.CodeReviewerEntity
import com.duxtinto.intellij.plugin.github.codereviews.helpers.random.RandomGenerator
import io.github.benas.randombeans.api.Randomizer

class CodeReviewerRandomizer : Randomizer<CodeReviewerEntity> {
    override fun getRandomValue(): CodeReviewerEntity {
        return CodeReviewerEntity(
                id = RandomGenerator.nextLong(),
                username = RandomGenerator.nextObject(String::class)
        )
    }
}
