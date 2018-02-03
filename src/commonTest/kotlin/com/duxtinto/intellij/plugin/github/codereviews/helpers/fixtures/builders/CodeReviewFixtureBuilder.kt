package com.duxtinto.intellij.plugin.github.codereviews.helpers.fixtures.builders

import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.PullRequestEntity
import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.reviews.CodeReviewEntity
import com.duxtinto.intellij.plugin.github.codereviews.helpers.fixtures.FixtureBuilder
import com.duxtinto.intellij.plugin.github.codereviews.helpers.random.RandomGenerator

class CodeReviewFixtureBuilder : FixtureBuilder<CodeReviewEntity> {
    private var pullRequest: PullRequestEntity = RandomGenerator.next()

    override fun build(): CodeReviewEntity {
        return RandomGenerator
                .next<CodeReviewEntity>()
                .copy(pull_request_id = pullRequest.number)
    }

    fun ofPullRequest(pullRequest: PullRequestEntity): CodeReviewFixtureBuilder {
        this.pullRequest = pullRequest
        return this
    }
}
