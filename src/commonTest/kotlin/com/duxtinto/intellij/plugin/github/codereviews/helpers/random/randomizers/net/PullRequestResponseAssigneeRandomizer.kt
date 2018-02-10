package com.duxtinto.intellij.plugin.github.codereviews.helpers.random.randomizers.net

import com.duxtinto.intellij.plugin.github.codereviews.helpers.random.RandomGenerator
import com.duxtinto.intellij.plugin.github.codereviews.net.pullrequests.apiv3.PullRequestResponse
import io.github.benas.randombeans.api.Randomizer

class PullRequestResponseAssigneeRandomizer : Randomizer<PullRequestResponse.Assignee> {
    override fun getRandomValue(): PullRequestResponse.Assignee {
        return PullRequestResponse.Assignee(
                id = RandomGenerator.next(),
                login = RandomGenerator.next()
        )
    }
}