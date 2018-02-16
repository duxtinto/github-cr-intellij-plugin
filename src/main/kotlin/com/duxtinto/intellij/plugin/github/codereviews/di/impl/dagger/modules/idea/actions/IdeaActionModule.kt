package com.duxtinto.intellij.plugin.github.codereviews.di.impl.dagger.modules.idea.actions

import com.intellij.openapi.actionSystem.ActionManager
import dagger.Module
import dagger.Provides

@Module(includes = [
    IdeaRevieweeActionModule::class,
    IdeaReviewerActionModule::class
])
class IdeaActionModule {
    @Provides
    fun provideActionManager(): ActionManager {
        return ActionManager.getInstance()
    }
}