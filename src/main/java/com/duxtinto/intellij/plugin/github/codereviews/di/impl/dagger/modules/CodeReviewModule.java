package com.duxtinto.intellij.plugin.github.codereviews.di.impl.dagger.modules;

import com.duxtinto.intellij.plugin.github.codereviews.di.scopes.ProjectScoped;
import com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow.codereviews.CodeReviews;
import com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow.codereviews.CodeReviewsView;
import dagger.Binds;
import dagger.Module;

@Module
public abstract class CodeReviewModule {
    @Binds
    @ProjectScoped
    public abstract CodeReviews.View provideCodeReviewsView(CodeReviewsView view);
}
