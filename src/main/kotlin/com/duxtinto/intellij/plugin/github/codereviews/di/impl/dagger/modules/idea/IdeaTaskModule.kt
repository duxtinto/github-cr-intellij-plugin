package com.duxtinto.intellij.plugin.github.codereviews.di.impl.dagger.modules.idea

import com.duxtinto.intellij.plugin.github.codereviews.di.scopes.ProjectScoped
import com.duxtinto.intellij.plugin.github.codereviews.ide.acl.entities.ProjectExt
import com.intellij.tasks.TaskManager
import dagger.Module
import dagger.Provides

@Module
class IdeaTaskModule {
    @Provides
    @ProjectScoped
    fun provideTaskManager(project: ProjectExt): TaskManager? {
        return project.getComponent(TaskManager::class.java)
    }
}