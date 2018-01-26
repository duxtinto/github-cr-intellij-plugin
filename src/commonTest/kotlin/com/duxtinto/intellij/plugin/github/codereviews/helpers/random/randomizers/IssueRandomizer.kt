package com.duxtinto.intellij.plugin.github.codereviews.helpers.random.randomizers

import com.duxtinto.intellij.plugin.github.codereviews.domain.issues.IssueEntity
import com.duxtinto.intellij.plugin.github.codereviews.helpers.random.RandomGenerator
import io.github.benas.randombeans.api.Randomizer

class IssueRandomizer : Randomizer<IssueEntity> {
    override fun getRandomValue(): IssueEntity {
        return IssueEntity(
                number = RandomGenerator.nextLong(),
                title = RandomGenerator.nextObject(String::class))
    }
}
