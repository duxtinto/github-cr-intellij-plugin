package com.duxtinto.intellij.plugin.github.codereviews.di.impl.dagger.modules;

import com.intellij.openapi.application.Application;
import com.intellij.openapi.application.ApplicationManager;
import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {
    @Provides
    public Application application() {
        return ApplicationManager.getApplication();
    }
}
