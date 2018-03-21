package com.duxtinto.intellij.plugin.github.codereviews.state

import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.PullRequestEntity

interface PluginStateHandler {
    var activeReviewerPullRequest: PullRequestEntity?
}
