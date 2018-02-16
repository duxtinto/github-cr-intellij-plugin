package com.duxtinto.intellij.plugin.github.codereviews.presentation.reviewee.pullrequests

import com.duxtinto.intellij.plugin.github.codereviews.di.qualifiers.Reviewee
import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.PullRequestEntity
import com.duxtinto.intellij.plugin.github.codereviews.presentation.pullrequestlist.PullRequestListPresenter
import com.intellij.util.ui.ColumnInfo
import javax.inject.Inject

class PullRequestListPresenter
    @Inject
    constructor(@Reviewee columnInfos: Array<ColumnInfo<PullRequestEntity, *>>)
    : PullRequestListPresenter(columnInfos)