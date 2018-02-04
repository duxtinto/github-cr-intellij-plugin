package com.duxtinto.intellij.plugin.github.codereviews.di.impl.dagger

import com.duxtinto.intellij.plugin.github.codereviews.di.impl.dagger.modules.ApplicationModule
import com.duxtinto.intellij.plugin.github.codereviews.di.impl.dagger.modules.ProjectModule
import dagger.Component

import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationContainer {
    fun projectContainer(projectModule: ProjectModule): ProjectContainer
}
