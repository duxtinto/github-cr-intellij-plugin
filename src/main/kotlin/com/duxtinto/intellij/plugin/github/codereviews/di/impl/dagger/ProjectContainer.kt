package com.duxtinto.intellij.plugin.github.codereviews.di.impl.dagger

import com.duxtinto.intellij.plugin.github.codereviews.di.impl.dagger.components.UiInjectors
import com.duxtinto.intellij.plugin.github.codereviews.di.impl.dagger.modules.project.ProjectModule
import com.duxtinto.intellij.plugin.github.codereviews.di.scopes.ProjectScoped
import com.intellij.dvcs.repo.VcsRepositoryMappingListener
import dagger.Subcomponent

@ProjectScoped
@Subcomponent(modules = [ProjectModule::class])
interface ProjectContainer : UiInjectors {
    fun repositoryListener(): VcsRepositoryMappingListener
}
