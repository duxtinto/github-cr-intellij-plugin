package com.duxtinto.intellij.plugin.github.codereviews.helpers.fixtures.builders

import com.duxtinto.intellij.plugin.github.codereviews.domain.issues.IssueEntity
import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.PullRequestEntity
import com.duxtinto.intellij.plugin.github.codereviews.helpers.fixtures.FixtureBuilder
import com.duxtinto.intellij.plugin.github.codereviews.helpers.random.RandomGenerator

class PullRequestFixtureBuilder : FixtureBuilder<PullRequestEntity> {
    private var number: Long = RandomGenerator.nextLong()
    private var closableIssues = arrayListOf<IssueEntity>()

    fun closes(issue: IssueEntity): PullRequestFixtureBuilder {
        closableIssues.add(issue)
        return this
    }

    override fun build(): PullRequestEntity {
        return RandomGenerator
                .nextObject(PullRequestEntity::class)
                .copy(number, closableIssues)
    }

    fun withNumber(number: Long): PullRequestFixtureBuilder {
        this.number = number
        return this
    }
}
