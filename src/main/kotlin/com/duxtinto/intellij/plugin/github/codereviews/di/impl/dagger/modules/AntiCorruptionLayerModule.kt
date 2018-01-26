package com.duxtinto.intellij.plugin.github.codereviews.di.impl.dagger.modules

import com.duxtinto.intellij.plugin.github.codereviews.di.scopes.ProjectScoped
import com.duxtinto.intellij.plugin.github.codereviews.ide.acl.entities.ProjectExt
import com.intellij.tasks.TaskManager
import dagger.Module
import dagger.Provides

@Module
class AntiCorruptionLayerModule {
    @Provides
    @ProjectScoped
    fun provideTaskManager(project: ProjectExt): TaskManager {
        return project.delegate().getComponent(TaskManager::class.java)
    }
}