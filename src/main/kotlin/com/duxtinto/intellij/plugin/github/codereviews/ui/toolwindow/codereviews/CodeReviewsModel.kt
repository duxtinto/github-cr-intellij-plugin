package com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow.codereviews

import org.jdesktop.swingx.treetable.DefaultMutableTreeTableNode
import javax.swing.tree.DefaultTreeModel

class CodeReviewsModel
    : DefaultTreeModel(DefaultMutableTreeTableNode()), CodeReviews.Model
