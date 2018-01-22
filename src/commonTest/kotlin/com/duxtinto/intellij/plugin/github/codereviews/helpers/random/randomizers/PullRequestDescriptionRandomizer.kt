package com.duxtinto.intellij.plugin.github.codereviews.helpers.random.randomizers

import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.PullRequestDescriptionEntity
import io.github.benas.randombeans.api.Randomizer

class PullRequestDescriptionRandomizer : Randomizer<PullRequestDescriptionEntity> {
    override fun getRandomValue(): PullRequestDescriptionEntity {
        return PullRequestDescriptionEntity(setOf())
    }
}