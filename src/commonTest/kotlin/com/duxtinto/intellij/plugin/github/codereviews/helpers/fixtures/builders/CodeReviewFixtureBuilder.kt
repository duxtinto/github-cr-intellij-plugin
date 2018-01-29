package com.duxtinto.intellij.plugin.github.codereviews.helpers.fixtures.builders

import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.reviews.CodeReviewEntity
import com.duxtinto.intellij.plugin.github.codereviews.helpers.fixtures.FixtureBuilder
import com.duxtinto.intellij.plugin.github.codereviews.helpers.random.RandomGenerator

class CodeReviewFixtureBuilder : FixtureBuilder<CodeReviewEntity> {
    override fun build(): CodeReviewEntity {
        return RandomGenerator
                .nextObject(CodeReviewEntity::class)
    }
}
