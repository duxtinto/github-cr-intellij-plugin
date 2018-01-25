package com.duxtinto.intellij.plugin.github.codereviews.di.impl.dagger.modules

import com.duxtinto.intellij.plugin.github.codereviews.di.scopes.ProjectScoped
import com.duxtinto.intellij.plugin.github.codereviews.ide.acl.entities.ProjectExt
import com.intellij.openapi.wm.ToolWindowManager
import com.intellij.openapi.wm.impl.ToolWindowImpl
import com.intellij.tasks.TaskManager
import com.intellij.ui.content.ContentManager
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class AntiCorruptionLayerModule {
    @Provides
    @ProjectScoped
    fun provideTaskManager(project: ProjectExt): TaskManager {
        return project.delegate().getComponent(TaskManager::class.java)
    }

    @Provides
    @ProjectScoped
    fun provideToolWindowManager(project: ProjectExt): ToolWindowManager {
        return ToolWindowManager.getInstance(project.delegate())
    }

    @Provides
    @ProjectScoped
    @Named("GH_Reviews")
    fun provideToolWindowImpl(toolWindowManager: ToolWindowManager): ToolWindowImpl {
        return toolWindowManager.getToolWindow("GitHub Reviews") as ToolWindowImpl
    }

    @Provides
    @ProjectScoped
    @Named("GH_Reviews")
    fun provideContentManager(@Named("GH_Reviews") toolWindow: ToolWindowImpl): ContentManager {
        return toolWindow.contentManager
    }
}