package com.duxtinto.intellij.plugin.github.codereviews.di.impl.dagger.modules

import com.duxtinto.intellij.plugin.github.codereviews.di.impl.dagger.modules.pullrequest.PullRequestModule
import com.duxtinto.intellij.plugin.github.codereviews.di.scopes.ProjectScoped
import com.duxtinto.intellij.plugin.github.codereviews.ide.acl.entities.ProjectExt
import com.intellij.openapi.progress.EmptyProgressIndicator
import com.intellij.openapi.progress.ProgressIndicator
import dagger.Module
import dagger.Provides

@Module(includes = [
    AntiCorruptionLayerModule::class,
    UserModule::class,
    ReposModule::class,
    PullRequestModule::class,
    CodeReviewModule::class,
    IssueModule::class,
    ToolWindowModule::class,
    EditorModule::class
])
class ProjectModule(private val project: ProjectExt) {

    @Provides
    @ProjectScoped
    fun provideProject(): ProjectExt {
        return this.project
    }

    @Provides
    @ProjectScoped
    fun provideProgressIndicator(): ProgressIndicator {
        return EmptyProgressIndicator()
    }
}
