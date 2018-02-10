package com.duxtinto.intellij.plugin.github.codereviews.presentation.pullrequestlist.columns

import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.PullRequestEntity
import com.intellij.util.ui.ColumnInfo

import javax.swing.*
import javax.swing.table.DefaultTableCellRenderer
import javax.swing.table.TableCellRenderer
import java.awt.*

class NumberColumnInfo : ColumnInfo<PullRequestEntity, Long>("number") {

    override fun valueOf(pullRequest: PullRequestEntity): Long? {
        return pullRequest.number
    }

    override fun getRenderer(pullRequest: PullRequestEntity?): TableCellRenderer? {
        return object : DefaultTableCellRenderer() {
            override fun getTableCellRendererComponent(table: JTable?, value: Any, isSelected: Boolean, hasFocus: Boolean, row: Int, column: Int): Component {
                val cellContent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column) as JLabel
                cellContent.text = "<html><a href=\"\">" + value.toString() + "</a></html>"
                return cellContent
            }
        }
    }
}
