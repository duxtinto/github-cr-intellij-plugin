package com.duxtinto.intellij.plugin.github.codereviews.helpers.fixtures.builders

import com.duxtinto.intellij.plugin.github.codereviews.domain.issues.IssueEntity
import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.PullRequestEntity
import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.reviews.CodeRevieweeEntity
import com.duxtinto.intellij.plugin.github.codereviews.helpers.fixtures.FixtureBuilder
import com.duxtinto.intellij.plugin.github.codereviews.helpers.random.RandomGenerator

class PullRequestFixtureBuilder : FixtureBuilder<PullRequestEntity> {
    private var number: Long = RandomGenerator.next()
    private var state: PullRequestEntity.State = RandomGenerator.next()
    private var reviewee: CodeRevieweeEntity = RandomGenerator.next()
    private var closableIssues = arrayListOf<IssueEntity>()

    override fun build(): PullRequestEntity {
        return RandomGenerator
                .next<PullRequestEntity>()
                .copy(
                        number = number,
                        state = state,
                        reviewee = reviewee,
                        closeableIssues = closableIssues)
    }

    fun withNumber(number: Long): PullRequestFixtureBuilder {
        this.number = number
        return this
    }

    fun withState(state: PullRequestEntity.State): PullRequestFixtureBuilder {
        this.state = state
        return this
    }

    fun forReviewee(reviewee: CodeRevieweeEntity): PullRequestFixtureBuilder {
        this.reviewee = reviewee
        return this
    }

    fun closes(issue: IssueEntity): PullRequestFixtureBuilder {
        closableIssues.add(issue)
        return this
    }
}
