package com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.reviews

import com.duxtinto.intellij.plugin.github.codereviews.domain.Interactor
import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.PullRequestEntity

interface CodeReviewsByPullRequestInteractor: Interactor<PullRequestEntity, List<CodeReviewEntity>>
