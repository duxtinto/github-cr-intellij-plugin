package com.duxtinto.intellij.plugin.github.codereviews.helpers.fixtures.builders.domain

import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.reviews.CodeRevieweeEntity
import com.duxtinto.intellij.plugin.github.codereviews.helpers.fixtures.FixtureBuilder
import com.duxtinto.intellij.plugin.github.codereviews.helpers.random.RandomGenerator

class CodeRevieweeFixtureBuilder: FixtureBuilder<CodeRevieweeEntity> {
    private var username: String = RandomGenerator.next()

    override fun build(): CodeRevieweeEntity {
        return RandomGenerator
                .next<CodeRevieweeEntity>()
                .copy(username = username)
    }

    fun withUsername(username: String): CodeRevieweeFixtureBuilder {
        this.username = username
        return this
    }
}
