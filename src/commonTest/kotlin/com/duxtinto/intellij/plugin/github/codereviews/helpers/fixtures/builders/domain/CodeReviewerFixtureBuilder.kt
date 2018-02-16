package com.duxtinto.intellij.plugin.github.codereviews.helpers.fixtures.builders.domain

import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.reviews.CodeReviewerEntity
import com.duxtinto.intellij.plugin.github.codereviews.helpers.fixtures.FixtureBuilder
import com.duxtinto.intellij.plugin.github.codereviews.helpers.random.RandomGenerator

class CodeReviewerFixtureBuilder: FixtureBuilder<CodeReviewerEntity> {
    private var username: String = RandomGenerator.next()

    override fun build(): CodeReviewerEntity {
        return RandomGenerator
                .next<CodeReviewerEntity>()
                .copy(username = username)
    }

    fun withUsername(username: String): CodeReviewerFixtureBuilder {
        this.username = username
        return this
    }
}
