package com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow.pullrequestlist.events

import com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow.pullrequestlist.PullRequestList
import javax.inject.Inject
import javax.swing.event.ListSelectionEvent

class SelectionListener
    @Inject
    constructor(): PullRequestList.View.Events.SelectionListener {
    override fun valueChanged(e: ListSelectionEvent?) {
    }
}