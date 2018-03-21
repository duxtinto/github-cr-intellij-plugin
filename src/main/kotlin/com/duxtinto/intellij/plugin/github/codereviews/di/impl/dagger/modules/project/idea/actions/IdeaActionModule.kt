package com.duxtinto.intellij.plugin.github.codereviews.di.impl.dagger.modules.project.idea.actions

import com.duxtinto.intellij.plugin.github.codereviews.di.scopes.ProjectScoped
import com.intellij.openapi.actionSystem.ActionManager
import dagger.Module
import dagger.Provides

@Module(includes = [
    IdeaRevieweeActionModule::class,
    IdeaReviewerActionModule::class
])
class IdeaActionModule {
    @Provides
    @ProjectScoped
    fun provideActionManager(): ActionManager {
        return ActionManager.getInstance()
    }
}