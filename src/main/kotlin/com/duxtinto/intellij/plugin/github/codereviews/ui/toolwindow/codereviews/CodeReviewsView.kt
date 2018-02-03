package com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow.codereviews

import com.intellij.ui.ScrollPaneFactory.createScrollPane
import com.intellij.uiDesigner.core.GridConstraints
import com.intellij.uiDesigner.core.GridLayoutManager
import com.intellij.util.ui.JBUI
import javax.inject.Inject
import javax.inject.Named
import javax.swing.JPanel
import javax.swing.tree.TreeCellRenderer

class CodeReviewsView
    @Inject
    constructor(
            mouseListener: CodeReviews.View.Events.MouseListener,
            @Named("code_reviews") private val treeCellRenderer: TreeCellRenderer)
    : CodeReviews.View {

    override val content = JPanel().apply {
        name = this.toString()
        layout = GridLayoutManager(1, 1, JBUI.emptyInsets(), -1, -1)
    }

    private var tree = CodeReviewsTree(CodeReviewsModel()).apply {
        name = "CodeReviews"
        cellRenderer = treeCellRenderer
        emptyText.appendText("Please, select a pull request above to see here its code reviews")
    }

    init {
        tree.addMouseListener(mouseListener)
        content.add(
                createScrollPane(tree),
                GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK or GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK or GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false)
        )
    }

    override fun render(model: CodeReviews.Model) {
        tree.model = model
        tree.repaint()
    }
}
