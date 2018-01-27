package com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow.pullrequestlist

import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.PullRequestEntity
import com.intellij.util.ui.ListTableModel
import javax.swing.JComponent
import javax.swing.event.ListSelectionListener
import java.awt.event.MouseListener as AwtMouseListener

interface PullRequestList {
    interface Model {
        val tableModel: ListTableModel<PullRequestEntity>
        fun setPullRequests(pullRequests: List<PullRequestEntity>)
    }

    interface View {
        val content: JComponent
        fun render(model: Model)

        interface Events {
            interface MouseListener : AwtMouseListener
            interface SelectionListener : ListSelectionListener
        }
    }

    interface Presenter {
        val model: Model
        fun displayPullRequests(pullRequests: List<PullRequestEntity>)
        fun setView(view: View)
    }
}
