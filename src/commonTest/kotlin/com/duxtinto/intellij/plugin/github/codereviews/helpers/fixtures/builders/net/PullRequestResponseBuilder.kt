package com.duxtinto.intellij.plugin.github.codereviews.helpers.fixtures.builders.net

import com.duxtinto.intellij.plugin.github.codereviews.helpers.fixtures.FixtureBuilder
import com.duxtinto.intellij.plugin.github.codereviews.helpers.random.RandomGenerator
import com.duxtinto.intellij.plugin.github.codereviews.net.pullrequests.apiv3.PullRequestResponse

class PullRequestResponseBuilder : FixtureBuilder<PullRequestResponse> {
    private var state: String = "open"
    private var assignee: PullRequestResponse.Assignee = RandomGenerator.next()

    override fun build(): PullRequestResponse {
        return RandomGenerator
                .next<PullRequestResponse>()
                .copy(state = state, assignee = assignee)
    }

    fun withState(state: String): PullRequestResponseBuilder {
        this.state = state
        return this
    }

    fun assignedTo(assignee: PullRequestResponse.Assignee): PullRequestResponseBuilder {
        this.assignee = assignee
        return this
    }
}
