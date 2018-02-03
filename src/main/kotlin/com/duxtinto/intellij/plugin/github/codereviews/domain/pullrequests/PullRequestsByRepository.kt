package com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests

import com.duxtinto.intellij.plugin.github.codereviews.domain.Interactor
import com.duxtinto.intellij.plugin.github.codereviews.domain.repositories.GithubRepositoryEntity

interface PullRequestsByRepository : Interactor<GithubRepositoryEntity, List<PullRequestEntity>>
