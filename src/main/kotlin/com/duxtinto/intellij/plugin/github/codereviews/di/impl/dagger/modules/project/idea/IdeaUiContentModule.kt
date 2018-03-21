package com.duxtinto.intellij.plugin.github.codereviews.di.impl.dagger.modules.project.idea

import com.duxtinto.intellij.plugin.github.codereviews.di.scopes.ProjectScoped
import com.intellij.openapi.wm.impl.ToolWindowImpl
import com.intellij.ui.content.ContentFactory
import com.intellij.ui.content.ContentManager
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class IdeaUiContentModule {
    @Provides
    @ProjectScoped
    @Named("GH_Reviews")
    fun provideContentManager(@Named("GH_Reviews") toolWindow: ToolWindowImpl): ContentManager {
        return toolWindow.contentManager
    }

    @Provides
    @ProjectScoped
    fun provideContentFactory(): ContentFactory {
        return ContentFactory.SERVICE.getInstance()
    }
}