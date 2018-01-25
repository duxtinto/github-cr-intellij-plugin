package com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow.pullrequestdetails

import org.jdesktop.swingx.treetable.DefaultMutableTreeTableNode
import javax.swing.tree.DefaultTreeModel

class PullRequestDetailsModel
    : DefaultTreeModel(DefaultMutableTreeTableNode()), PullRequestDetails.Model
