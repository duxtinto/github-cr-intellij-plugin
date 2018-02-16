package com.duxtinto.intellij.plugin.github.codereviews.di.impl.dagger.modules.pullrequest

import com.duxtinto.intellij.plugin.github.codereviews.di.qualifiers.Reviewee
import com.duxtinto.intellij.plugin.github.codereviews.di.qualifiers.Reviewer
import com.duxtinto.intellij.plugin.github.codereviews.di.scopes.ProjectScoped
import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.PullRequestEntity
import com.duxtinto.intellij.plugin.github.codereviews.presentation.pullrequestlist.columns.ColumnInfoFactory
import com.intellij.util.ui.ColumnInfo
import dagger.Module
import dagger.Provides

@Module(includes = [
    CommonModule::class,
    RevieweeModule::class,
    ReviewerModule::class
])
class PullRequestModule {
    @Provides
    @ProjectScoped
    @Reviewee
    fun provideRevieweePullRequestColumnInfoDefaultArray(@Reviewee columnInfoFactory: ColumnInfoFactory): Array<ColumnInfo<PullRequestEntity, *>> {
        return columnInfoFactory.createDefaultColumns()
    }

    @Provides
    @ProjectScoped
    @Reviewer
    fun provideReviewerPullRequestColumnInfoDefaultArray(@Reviewer columnInfoFactory: ColumnInfoFactory): Array<ColumnInfo<PullRequestEntity, *>> {
        return columnInfoFactory.createDefaultColumns()
    }
}
