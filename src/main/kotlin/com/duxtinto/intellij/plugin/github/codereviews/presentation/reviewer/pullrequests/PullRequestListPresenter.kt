package com.duxtinto.intellij.plugin.github.codereviews.presentation.reviewer.pullrequests

import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.PullRequestEntity
import com.duxtinto.intellij.plugin.github.codereviews.presentation.pullrequestlist.PullRequestListPresenter
import com.intellij.util.ui.ColumnInfo
import javax.inject.Inject
import javax.inject.Named

class PullRequestListPresenter
    @Inject
    constructor(@Named("default") columnInfos: Array<ColumnInfo<PullRequestEntity, *>>)
    : PullRequestListPresenter(columnInfos)