package com.duxtinto.intellij.plugin.github.codereviews.helpers.random.randomizers.net

import com.duxtinto.intellij.plugin.github.codereviews.helpers.random.RandomGenerator
import com.duxtinto.intellij.plugin.github.codereviews.net.pullrequests.apiv3.PullRequestResponse
import io.github.benas.randombeans.api.Randomizer

class PullRequestResponseRandomizer : Randomizer<PullRequestResponse> {
    override fun getRandomValue(): PullRequestResponse {
        val prNumber: Long = RandomGenerator.next()
        val body: String = RandomGenerator.next()

        return PullRequestResponse(
                number = prNumber,
                state = RandomGenerator.next(),
                title = RandomGenerator.next(),
                body = body,
                bodyHtml = body,
                bodyText = body,
                htmlUrl = "https://github.com/duxtinto/dummy/pull/$prNumber",
                head = RandomGenerator.next(),
                assignee = RandomGenerator.next())
    }
}
