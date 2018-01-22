package com.duxtinto.intellij.plugin.github.codereviews.domain.issues;

import com.duxtinto.intellij.plugin.github.codereviews.domain.Interactor;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.inject.Inject;

import static com.google.common.base.Preconditions.checkNotNull;

public class SwitchToIssueInteractor implements Interactor<IssueEntity, Void> {
    @Nonnull
    private final IssuesDomainContract.Switcher issueSwitcher;

    @Inject
    public SwitchToIssueInteractor(
            @Nonnull IssuesDomainContract.Switcher issueSwitcher) {
        this.issueSwitcher = checkNotNull(issueSwitcher);
    }

    @Nullable
    @Override
    public Void run(IssueEntity issue) {
        issueSwitcher.switchToIssue(issue);
        return null;
    }
}
