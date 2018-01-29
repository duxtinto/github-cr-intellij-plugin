package com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow.pullrequestlist

import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.PullRequestEntity
import com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow.pullrequestlist.events.PullRequestListMouseListener
import com.intellij.ui.components.JBScrollPane
import com.intellij.ui.table.TableView
import com.intellij.uiDesigner.core.GridConstraints
import com.intellij.uiDesigner.core.GridLayoutManager
import com.intellij.util.ui.ColumnInfo
import com.intellij.util.ui.JBUI
import com.intellij.util.ui.ListTableModel
import javax.inject.Inject
import javax.inject.Named
import javax.swing.JPanel
import javax.swing.ListSelectionModel

class PullRequestListView
@Inject
constructor(
        @Named("default") private val defaultColumns: Array<ColumnInfo<PullRequestEntity,*>>,
        mouseListener: PullRequestListMouseListener,
        private val selectionListener: PullRequestList.View.Events.SelectionListener
) : PullRequestList.View {

    private var table: TableView<PullRequestEntity>

    override val content: JPanel = JPanel().apply {
        name = this.toString()
        layout = GridLayoutManager(1, 1, JBUI.emptyInsets(), -1, -1)
    }

    init {
        val model = ListTableModel<PullRequestEntity>(*defaultColumns)
        table = TableView<PullRequestEntity>(model)
        with(table) {
            with(selectionModel) {
                selectionMode = ListSelectionModel.SINGLE_SELECTION
                addListSelectionListener(selectionListener)
            }
            selectionListener.setTable(this)
            addMouseListener(mouseListener)
            emptyText.appendText("Nothing to see here.")
        }

        content.add(
                JBScrollPane().apply { setViewportView(table) },
                GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK or GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK or GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false)
        )
    }

    override fun render(model: PullRequestList.Model) {
        table.setModelAndUpdateColumns(model.tableModel)
        table.repaint()
    }
}
