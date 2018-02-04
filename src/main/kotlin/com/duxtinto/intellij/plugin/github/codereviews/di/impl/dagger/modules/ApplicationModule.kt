package com.duxtinto.intellij.plugin.github.codereviews.di.impl.dagger.modules

import com.intellij.openapi.application.Application
import com.intellij.openapi.application.ApplicationManager
import com.intellij.ui.content.ContentFactory
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule {
    @Provides
    fun provideApplication(): Application {
        return ApplicationManager.getApplication()
    }

    @Provides
    fun provideContentFactory(): ContentFactory {
        return ContentFactory.SERVICE.getInstance()
    }
}
