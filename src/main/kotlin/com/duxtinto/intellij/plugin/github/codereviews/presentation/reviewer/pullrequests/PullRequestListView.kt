package com.duxtinto.intellij.plugin.github.codereviews.presentation.reviewer.pullrequests

import com.duxtinto.intellij.plugin.github.codereviews.di.qualifiers.Reviewer
import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.PullRequestEntity
import com.duxtinto.intellij.plugin.github.codereviews.presentation.pullrequestlist.PullRequestList
import com.duxtinto.intellij.plugin.github.codereviews.presentation.pullrequestlist.PullRequestListView
import com.intellij.util.ui.ColumnInfo
import javax.inject.Inject

class PullRequestListView
@Inject
constructor(
        @Reviewer private val defaultColumns: Array<ColumnInfo<PullRequestEntity, *>>,
        @Reviewer mouseListener: PullRequestList.View.Events.MouseListener,
        @Reviewer private val selectionListener: PullRequestList.View.Events.SelectionListener
): PullRequestListView(defaultColumns, mouseListener, selectionListener)
