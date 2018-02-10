package com.duxtinto.intellij.plugin.github.codereviews.helpers.random.randomizers.net

import com.duxtinto.intellij.plugin.github.codereviews.helpers.random.RandomGenerator
import com.duxtinto.intellij.plugin.github.codereviews.net.pullrequests.apiv3.PullRequestResponse
import io.github.benas.randombeans.api.Randomizer

class PullRequestResponseLinkRandomizer: Randomizer<PullRequestResponse.Link> {
    override fun getRandomValue(): PullRequestResponse.Link {
        return PullRequestResponse.Link(
                label = RandomGenerator.next(),
                ref = RandomGenerator.next(),
                sha = RandomGenerator.next()
        )
    }
}
