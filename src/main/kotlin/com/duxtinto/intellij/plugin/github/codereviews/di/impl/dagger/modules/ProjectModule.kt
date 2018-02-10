package com.duxtinto.intellij.plugin.github.codereviews.di.impl.dagger.modules

import com.duxtinto.intellij.plugin.github.codereviews.di.scopes.ProjectScoped
import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.PullRequestEntity
import com.duxtinto.intellij.plugin.github.codereviews.ide.acl.entities.ProjectExt
import com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow.pullrequestlist.columns.ColumnInfoFactory
import com.intellij.openapi.progress.EmptyProgressIndicator
import com.intellij.openapi.progress.ProgressIndicator
import com.intellij.util.ui.ColumnInfo
import dagger.Module
import dagger.Provides

import javax.inject.Named

@Module(includes = [
    AntiCorruptionLayerModule::class,
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

    @Provides
    @ProjectScoped
    @Named("default")
    fun providePullRequestColumnInfoDefaultArray(columnInfoFactory: ColumnInfoFactory): Array<ColumnInfo<PullRequestEntity, *>> {
        return columnInfoFactory.createDefaultColumns()
    }
}
