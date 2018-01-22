package com.duxtinto.intellij.plugin.github.codereviews.helpers.fixtures.builders

import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.PullRequestDescriptionEntity
import com.duxtinto.intellij.plugin.github.codereviews.helpers.fixtures.FixtureBuilder
import com.duxtinto.intellij.plugin.github.codereviews.helpers.random.RandomGenerator

class PullRequestDescriptionFixtureBuilder : FixtureBuilder<PullRequestDescriptionEntity> {
    private var closableIssueIds: Collection<Long> = setOf()

    override fun build(): PullRequestDescriptionEntity {
        return RandomGenerator
                .nextObject(PullRequestDescriptionEntity::class)
                .copy(closableIssues = closableIssueIds)
    }

    fun thatCloses(issueIds: Collection<Long>): PullRequestDescriptionFixtureBuilder {
        closableIssueIds = issueIds
        return this
    }
}