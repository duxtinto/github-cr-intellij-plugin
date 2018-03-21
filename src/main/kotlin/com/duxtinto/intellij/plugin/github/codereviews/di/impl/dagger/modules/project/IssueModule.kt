package com.duxtinto.intellij.plugin.github.codereviews.di.impl.dagger.modules.project

import com.duxtinto.intellij.plugin.github.codereviews.di.scopes.ProjectScoped
import com.duxtinto.intellij.plugin.github.codereviews.domain.issues.IssuesDomainContract
import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.issues.GetAllClosableByInteractor
import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.issues.ParseIssuesFromStringInteractor
import com.duxtinto.intellij.plugin.github.codereviews.ide.acl.services.tasks.IdeaTaskActivator
import com.duxtinto.intellij.plugin.github.codereviews.net.issues.apiv3.IssueFetcher
import dagger.Binds
import dagger.Module

@Module
abstract class IssueModule {
    @Binds
    @ProjectScoped
    abstract fun provideGetAllClosableByInteractor(interactor: GetAllClosableByInteractor): ParseIssuesFromStringInteractor

    @Binds
    @ProjectScoped
    abstract fun provideIssuesDomainContractSwitcher(switcher: IdeaTaskActivator): IssuesDomainContract.Switcher

    @Binds
    @ProjectScoped
    abstract fun provideIssuesDomainContractFetcher(fetcher: IssueFetcher): IssuesDomainContract.Fetcher
}
