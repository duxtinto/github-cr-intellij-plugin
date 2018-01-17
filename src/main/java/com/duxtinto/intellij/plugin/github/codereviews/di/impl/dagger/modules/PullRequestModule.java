package com.duxtinto.intellij.plugin.github.codereviews.di.impl.dagger.modules;

import com.duxtinto.intellij.plugin.github.codereviews.data.pullrequests.PullRequestRepositoryImpl;
import com.duxtinto.intellij.plugin.github.codereviews.di.scopes.ProjectScoped;
import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.PullRequestDomainContract;
import com.duxtinto.intellij.plugin.github.codereviews.net.pullrequests.ApiV3PullRequestFetcher;
import com.duxtinto.intellij.plugin.github.codereviews.services.pullrequests.GithubDescriptionParser;
import com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow.pullrequestlist.PullRequestList;
import com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow.pullrequestlist.PullRequestListPresenter;
import com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow.pullrequestlist.PullRequestListView;
import dagger.Binds;
import dagger.Module;

@Module
public abstract class PullRequestModule {
    @Binds
    @ProjectScoped
    public abstract PullRequestList.Presenter providePullRequestListPresenter(PullRequestListPresenter presenter);

    @Binds
    @ProjectScoped
    public abstract PullRequestList.View providePullRequestListView(PullRequestListView view);

    @Binds
    public abstract PullRequestDomainContract.Fetcher providePullRequestDomainContractFetcher(ApiV3PullRequestFetcher fetcher);

    @Binds
    public abstract PullRequestDomainContract.Repository providePullRequestDomainContractRepository(PullRequestRepositoryImpl repository);

    @Binds
    public abstract PullRequestDomainContract.DescriptionParser providePullRequestDomainContractDescriptionParser(GithubDescriptionParser parser);
}
