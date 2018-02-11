package com.duxtinto.intellij.plugin.github.codereviews.ide.acl.presentation.toolwindow

interface ToolWindowContent {
    interface Model

    interface Presenter {
        fun displayToolWindow()
    }
}
