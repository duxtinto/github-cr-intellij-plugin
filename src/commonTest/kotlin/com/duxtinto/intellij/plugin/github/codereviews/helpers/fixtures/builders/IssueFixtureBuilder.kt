package com.duxtinto.intellij.plugin.github.codereviews.helpers.fixtures.builders

import com.duxtinto.intellij.plugin.github.codereviews.domain.issues.IssueEntity
import com.duxtinto.intellij.plugin.github.codereviews.helpers.random.RandomGenerator
import com.duxtinto.intellij.plugin.github.codereviews.helpers.fixtures.FixtureBuilder

class IssueFixtureBuilder : FixtureBuilder<IssueEntity> {
    override fun build(): IssueEntity {
        return RandomGenerator.next()
    }
}
