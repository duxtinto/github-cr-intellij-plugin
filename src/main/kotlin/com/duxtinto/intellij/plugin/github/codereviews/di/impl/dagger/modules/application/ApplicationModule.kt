package com.duxtinto.intellij.plugin.github.codereviews.di.impl.dagger.modules.application

import com.intellij.openapi.application.Application
import com.intellij.openapi.application.ApplicationManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [StateModule::class])
@Singleton
class ApplicationModule {
    @Provides
    @Singleton
    fun provideApplication(): Application {
        return ApplicationManager.getApplication()
    }
}
