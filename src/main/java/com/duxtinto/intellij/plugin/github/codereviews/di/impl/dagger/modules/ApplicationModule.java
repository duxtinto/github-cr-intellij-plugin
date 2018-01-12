package com.duxtinto.intellij.plugin.github.codereviews.di.impl.dagger.modules;

import com.intellij.openapi.application.Application;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.ui.content.ContentFactory;
import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {
    @Provides
    public Application provideApplication() {
        return ApplicationManager.getApplication();
    }

    @Provides
    public ContentFactory provideContentFactory() {
        return ContentFactory.SERVICE.getInstance();
    }
}
