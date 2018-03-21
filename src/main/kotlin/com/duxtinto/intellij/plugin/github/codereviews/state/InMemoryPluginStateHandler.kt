package com.duxtinto.intellij.plugin.github.codereviews.state

import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.PullRequestEntity
import javax.inject.Inject

class InMemoryPluginStateHandler
    @Inject constructor()
    : PluginStateHandler {
    override var activeReviewerPullRequest: PullRequestEntity? = null
}
