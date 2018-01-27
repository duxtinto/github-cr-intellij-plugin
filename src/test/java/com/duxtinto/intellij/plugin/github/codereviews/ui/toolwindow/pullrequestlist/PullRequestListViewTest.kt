package com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow.pullrequestlist

import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.PullRequestEntity
import com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow.pullrequestlist.events.PullRequestListMouseListener
import com.intellij.ui.components.JBScrollPane
import com.intellij.ui.table.TableView
import com.intellij.util.ui.ColumnInfo
import com.intellij.util.ui.ListTableModel
import mockit.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import javax.swing.JComponent
import javax.swing.JPanel
import javax.swing.ListSelectionModel

internal class PullRequestListViewTest {

    @Injectable
    private var columnInfos = arrayOf<ColumnInfo<PullRequestEntity, *>>()

    @Injectable
    private lateinit var mouseListener: PullRequestListMouseListener

    @Injectable
    private lateinit var selectionListener: PullRequestList.View.Events.SelectionListener

    @Test
    @DisplayName("initialization should register the listeners and initialize the content")
    internal fun init(
            @Mocked table: TableView<PullRequestEntity>,
            @Mocked scrollPane: JBScrollPane,
            @Mocked selectionModel: ListSelectionModel,
            @Mocked content: JPanel) {
        // Arrange
        object : Expectations() {
            init {
                TableView<PullRequestEntity>(any as ListTableModel<PullRequestEntity>?)
                result = table
            }
        }

        // Act
        PullRequestListView(columnInfos, mouseListener, selectionListener)

        // Assert
        object : Verifications() {
            init {
                selectionModel.addListSelectionListener(selectionListener)
                table.addMouseListener(mouseListener)
                content.add(any as JComponent?, any)
            }
        }
    }

    @Test
    @DisplayName("render should update & repaint the table and its content")
    fun renderHappyPath(
            @Mocked table: TableView<PullRequestEntity>,
            @Mocked scrollPane: JBScrollPane) {
        // Arrange
        object : Expectations() {
            init {
                TableView<PullRequestEntity>(any as ListTableModel<PullRequestEntity>?)
                result = table
            }
        }

        val view = PullRequestListView(columnInfos, mouseListener, selectionListener)
        val model: PullRequestList.Model = PullRequestListModel(columnInfos)

        // Act
        view.render(model)

        // Assert
        object : VerificationsInOrder() {
            init {
                table.setModelAndUpdateColumns(model.tableModel)
                table.repaint()
            }
        }

        object : FullVerifications(mouseListener) {
            init {}
        }

        object : FullVerifications(selectionListener) {
            init {}
        }
    }
}