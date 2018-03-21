package com.duxtinto.intellij.plugin.github.codereviews.di.impl.dagger.modules.application

import com.duxtinto.intellij.plugin.github.codereviews.state.PluginStateHandler
import com.duxtinto.intellij.plugin.github.codereviews.state.InMemoryPluginStateHandler
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class StateModule {
    @Binds
    @Singleton
    abstract fun providePluginStateHandler(handler: InMemoryPluginStateHandler): PluginStateHandler
}
