package com.duxtinto.intellij.plugin.github.codereviews.di.impl.dagger.modules.project

import com.duxtinto.intellij.plugin.github.codereviews.di.scopes.ProjectScoped
import com.duxtinto.intellij.plugin.github.codereviews.domain.DomainContract
import com.duxtinto.intellij.plugin.github.codereviews.ide.acl.services.editor.IdeaEditorDriver
import dagger.Binds
import dagger.Module

@Module
abstract class EditorModule {
    @Binds
    @ProjectScoped
    abstract fun provideEditorDriver(switcher: IdeaEditorDriver): DomainContract.EditorDriver
}