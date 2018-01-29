package com.duxtinto.intellij.plugin.github.codereviews.di.impl.dagger.modules;

import com.duxtinto.intellij.plugin.github.codereviews.data.codereviews.CodeReviewsDataContract;
import com.duxtinto.intellij.plugin.github.codereviews.data.codereviews.CodeReviewsRepository;
import com.duxtinto.intellij.plugin.github.codereviews.di.scopes.ProjectScoped;
import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.reviews.CodeReviewsDomainContract;
import com.duxtinto.intellij.plugin.github.codereviews.net.codereviews.apiv3.CodeReviewsFetcher;
import com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow.codereviews.CodeReviews;
import com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow.codereviews.CodeReviewsPresenter;
import com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow.codereviews.CodeReviewsView;
import dagger.Binds;
import dagger.Module;

@Module
public abstract class CodeReviewModule {
    @Binds
    @ProjectScoped
    public abstract CodeReviewsDomainContract.Repository provideCodeReviewsDomainContractRepository(CodeReviewsRepository repository);

    @Binds
    @ProjectScoped
    public abstract CodeReviewsDataContract.Fetcher provideCodeReviewsDataContractFetcher(CodeReviewsFetcher fetcher);

    @Binds
    @ProjectScoped
    public abstract CodeReviews.View provideCodeReviewsView(CodeReviewsView view);

    @Binds
    @ProjectScoped
    public abstract CodeReviews.Presenter provideCodeReviewsPresenter(CodeReviewsPresenter presenter);
}
