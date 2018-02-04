package com.duxtinto.intellij.plugin.github.codereviews.di.impl.dagger.modules.idea

import com.duxtinto.intellij.plugin.github.codereviews.di.scopes.ProjectScoped
import com.duxtinto.intellij.plugin.github.codereviews.ide.acl.entities.ProjectExt
import com.intellij.openapi.wm.ToolWindowManager
import com.intellij.openapi.wm.impl.ToolWindowImpl
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class IdeaToolWindowModule {
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
}