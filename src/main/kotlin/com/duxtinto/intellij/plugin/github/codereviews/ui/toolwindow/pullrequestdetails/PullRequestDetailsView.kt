package com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow.pullrequestdetails

import com.intellij.ui.ScrollPaneFactory.createScrollPane
import com.intellij.ui.treeStructure.Tree
import com.intellij.uiDesigner.core.GridConstraints
import com.intellij.uiDesigner.core.GridLayoutManager
import com.intellij.util.ui.JBUI
import javax.inject.Inject
import javax.swing.JComponent
import javax.swing.JPanel

class PullRequestDetailsView
    @Inject constructor()
    : PullRequestDetails.View {

    override val content: JComponent = initContent()
    lateinit var tree: Tree

    private fun initContent() : JPanel {
        val content = JPanel()

        content.layout = GridLayoutManager(1, 1, JBUI.emptyInsets(), -1, -1)

        tree = Tree(PullRequestDetailsModel())
        tree.emptyText
                .clear()
                .appendText("Please, select a pull request above to see the here its code reviews")

        val scrollPane = createScrollPane(tree)
        content.add(scrollPane, GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK or GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK or GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false))

        return content
    }
}
