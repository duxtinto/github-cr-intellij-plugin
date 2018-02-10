package com.duxtinto.intellij.plugin.github.codereviews.presentation.toolwindow

import com.duxtinto.intellij.plugin.github.codereviews.di.contracts.DiContainerAware
import com.intellij.openapi.project.DumbAware
import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.impl.ToolWindowImpl
import javax.inject.Inject
import javax.inject.Named

class ToolWindowFactory : com.intellij.openapi.wm.ToolWindowFactory, DiContainerAware, DumbAware {
    @Inject
    @field:Named("GH_Reviews")
    internal lateinit var myToolWindow: ToolWindowImpl

    @Inject
    internal lateinit var presenter: ToolWindowContent.Presenter

    override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {
        getProjectContainer(project).inject(this)

        if (myToolWindow.id != (toolWindow as ToolWindowImpl).id) {
            return
        }

        presenter.displayToolWindow()
    }
}
