package com.duxtinto.intellij.plugin.github.codereviews.presentation.reviewer.pullrequests

import com.duxtinto.intellij.plugin.github.codereviews.di.qualifiers.Reviewer
import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.PullRequestEntity
import com.duxtinto.intellij.plugin.github.codereviews.presentation.pullrequestlist.PullRequestList
import com.duxtinto.intellij.plugin.github.codereviews.presentation.pullrequestlist.PullRequestListView
import com.duxtinto.intellij.plugin.github.codereviews.presentation.pullrequestlist.events.PullRequestListMouseListener
import com.intellij.util.ui.ColumnInfo
import javax.inject.Inject
import javax.inject.Named

class PullRequestListView
@Inject
constructor(
        @Named("default") private val defaultColumns: Array<ColumnInfo<PullRequestEntity, *>>,
        mouseListener: PullRequestListMouseListener,
        @Reviewer private val selectionListener: PullRequestList.View.Events.SelectionListener
): PullRequestListView(defaultColumns, mouseListener, selectionListener)