package com.duxtinto.intellij.plugin.github.codereviews.helpers.fixtures

import com.duxtinto.intellij.plugin.github.codereviews.helpers.fixtures.builders.IssueFixtureBuilder
import com.duxtinto.intellij.plugin.github.codereviews.helpers.fixtures.builders.PullRequestDescriptionFixtureBuilder
import com.duxtinto.intellij.plugin.github.codereviews.helpers.fixtures.builders.PullRequestFixtureBuilder
import com.duxtinto.intellij.plugin.github.codereviews.helpers.fixtures.builders.RepositoryFixtureBuilder

object Fixture {
    internal fun issue(): IssueFixtureBuilder {
        return IssueFixtureBuilder()
    }

    internal fun pullRequest(): PullRequestFixtureBuilder {
        return PullRequestFixtureBuilder()
    }

    fun pullRequestDescription(): PullRequestDescriptionFixtureBuilder {
        return PullRequestDescriptionFixtureBuilder()
    }

    fun repository(): RepositoryFixtureBuilder {
        return RepositoryFixtureBuilder()
    }

    internal fun ide(): IdeFixture {
        return IdeFixture()
    }
}
