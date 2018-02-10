package com.duxtinto.intellij.plugin.github.codereviews.helpers.fixtures.builders.net

import com.duxtinto.intellij.plugin.github.codereviews.helpers.fixtures.FixtureBuilder
import com.duxtinto.intellij.plugin.github.codereviews.helpers.random.RandomGenerator
import com.duxtinto.intellij.plugin.github.codereviews.net.pullrequests.apiv3.PullRequestResponse

class PullRequestResponseAssigneeBuilder : FixtureBuilder<PullRequestResponse.Assignee> {
    override fun build(): PullRequestResponse.Assignee {
        return RandomGenerator.next()
    }
}
