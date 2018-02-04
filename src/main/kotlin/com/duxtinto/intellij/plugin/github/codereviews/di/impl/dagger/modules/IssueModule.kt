package com.duxtinto.intellij.plugin.github.codereviews.di.impl.dagger.modules

import com.duxtinto.intellij.plugin.github.codereviews.domain.issues.IssuesDomainContract
import com.duxtinto.intellij.plugin.github.codereviews.ide.acl.services.tasks.IdeaTaskActivator
import com.duxtinto.intellij.plugin.github.codereviews.net.issues.ApiV3IssueFetcher
import dagger.Binds
import dagger.Module

@Module
abstract class IssueModule {
    @Binds
    abstract fun provideIssuesDomainContractSwitcher(switcher: IdeaTaskActivator): IssuesDomainContract.Switcher

    @Binds
    abstract fun provideIssuesDomainContractFetcher(fetcher: ApiV3IssueFetcher): IssuesDomainContract.Fetcher
}
