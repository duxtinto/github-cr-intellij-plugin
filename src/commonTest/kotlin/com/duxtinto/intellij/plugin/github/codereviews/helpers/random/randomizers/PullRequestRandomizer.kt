package com.duxtinto.intellij.plugin.github.codereviews.helpers.random.randomizers

import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.PullRequestEntity
import com.duxtinto.intellij.plugin.github.codereviews.helpers.random.RandomGenerator
import io.github.benas.randombeans.api.Randomizer
import java.net.URL

class PullRequestRandomizer : Randomizer<PullRequestEntity> {
    override fun getRandomValue(): PullRequestEntity {
        val prNumber = RandomGenerator.nextLong()
        return PullRequestEntity(
                number = prNumber,
                closeableIssues = listOf(),
                title = RandomGenerator.nextObject(String::class),
                state = RandomGenerator.nextObject(PullRequestEntity.State::class),
                url = URL("https://github.com/duxtinto/dummy/pull/$prNumber"))
    }
}
