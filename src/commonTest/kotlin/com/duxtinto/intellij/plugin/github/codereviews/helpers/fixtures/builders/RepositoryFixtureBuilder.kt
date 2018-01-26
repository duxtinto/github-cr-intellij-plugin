package com.duxtinto.intellij.plugin.github.codereviews.helpers.fixtures.builders

import com.duxtinto.intellij.plugin.github.codereviews.domain.repositories.GithubRepositoryEntity
import com.duxtinto.intellij.plugin.github.codereviews.helpers.fixtures.FixtureBuilder
import com.duxtinto.intellij.plugin.github.codereviews.helpers.random.RandomGenerator

class RepositoryFixtureBuilder : FixtureBuilder<GithubRepositoryEntity> {
    override fun build(): GithubRepositoryEntity {
        return RandomGenerator.nextObject(GithubRepositoryEntity::class)
    }
}
