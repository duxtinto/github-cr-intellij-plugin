package com.duxtinto.intellij.plugin.github.codereviews.di.impl.dagger.modules;

import com.duxtinto.intellij.plugin.github.codereviews.domain.issues.IssuesDomainContract;
import com.duxtinto.intellij.plugin.github.codereviews.ide.acl.services.tasks.IdeaTaskActivator;
import com.duxtinto.intellij.plugin.github.codereviews.net.issues.ApiV3IssueFetcher;
import dagger.Binds;
import dagger.Module;

@Module
public abstract class IssueModule {
    @Binds
    public abstract IssuesDomainContract.Switcher provideIssuesDomainContractSwitcher(IdeaTaskActivator switcher);

    @Binds
    public abstract IssuesDomainContract.Fetcher provideIssuesDomainContractFetcher(ApiV3IssueFetcher fetcher);
}
