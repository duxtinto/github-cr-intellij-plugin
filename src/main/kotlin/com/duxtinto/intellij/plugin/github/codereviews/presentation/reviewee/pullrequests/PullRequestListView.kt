package com.duxtinto.intellij.plugin.github.codereviews.presentation.reviewee.pullrequests

import com.duxtinto.intellij.plugin.github.codereviews.di.qualifiers.Reviewee
import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.PullRequestEntity
import com.duxtinto.intellij.plugin.github.codereviews.presentation.pullrequestlist.PullRequestList
import com.duxtinto.intellij.plugin.github.codereviews.presentation.pullrequestlist.PullRequestListView
import com.intellij.util.ui.ColumnInfo
import javax.inject.Inject

class PullRequestListView
@Inject
constructor(
        @Reviewee private val defaultColumns: Array<ColumnInfo<PullRequestEntity, *>>,
        @Reviewee private val mouseListener: PullRequestList.View.Events.MouseListener,
        @Reviewee private val selectionListener: PullRequestList.View.Events.SelectionListener
): PullRequestListView(defaultColumns, mouseListener, selectionListener)
