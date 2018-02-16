package com.duxtinto.intellij.plugin.github.codereviews.helpers.fixtures

import com.duxtinto.intellij.plugin.github.codereviews.helpers.fixtures.builders.*
import com.duxtinto.intellij.plugin.github.codereviews.helpers.fixtures.builders.domain.CodeRevieweeFixtureBuilder
import com.duxtinto.intellij.plugin.github.codereviews.helpers.fixtures.builders.domain.CodeReviewerFixtureBuilder
import com.duxtinto.intellij.plugin.github.codereviews.helpers.fixtures.builders.domain.UserFixtureBuilder

object Fixture {
    internal fun issue(): IssueFixtureBuilder {
        return IssueFixtureBuilder()
    }

    internal fun pullRequest(): PullRequestFixtureBuilder {
        return PullRequestFixtureBuilder()
    }

    internal fun pullRequestDescription(): PullRequestDescriptionFixtureBuilder {
        return PullRequestDescriptionFixtureBuilder()
    }

    internal fun repository(): RepositoryFixtureBuilder {
        return RepositoryFixtureBuilder()
    }

    internal fun codeReview(): CodeReviewFixtureBuilder {
        return CodeReviewFixtureBuilder()
    }

    internal fun reviewComment(): CodeReviewCommentFixtureBuilder {
        return CodeReviewCommentFixtureBuilder()
    }

    internal fun ide(): IdeFixture {
        return IdeFixture()
    }

    fun net(): NetFixture {
        return NetFixture()
    }

    fun reviewee(): CodeRevieweeFixtureBuilder {
        return CodeRevieweeFixtureBuilder()
    }

    fun user(): UserFixtureBuilder {
        return UserFixtureBuilder()
    }

    fun reviewer(): CodeReviewerFixtureBuilder {
        return CodeReviewerFixtureBuilder()
    }
}
