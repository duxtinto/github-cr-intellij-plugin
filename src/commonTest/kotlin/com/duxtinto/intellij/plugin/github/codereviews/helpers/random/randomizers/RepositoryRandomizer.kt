package com.duxtinto.intellij.plugin.github.codereviews.helpers.random.randomizers

import com.duxtinto.intellij.plugin.github.codereviews.domain.repositories.GithubRepositoryEntity
import com.duxtinto.intellij.plugin.github.codereviews.helpers.random.RandomGenerator
import io.github.benas.randombeans.api.Randomizer

class RepositoryRandomizer : Randomizer<GithubRepositoryEntity> {
    override fun getRandomValue(): GithubRepositoryEntity {
        return GithubRepositoryEntity(RandomGenerator.nextObject(String::class), RandomGenerator.nextObject(String::class))
    }
}
