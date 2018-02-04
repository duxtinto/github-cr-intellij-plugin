package com.duxtinto.intellij.plugin.github.codereviews.domain.issues

import com.duxtinto.intellij.plugin.github.codereviews.domain.Interactor
import javax.inject.Inject

import com.google.common.base.Preconditions.checkNotNull

class SwitchToIssueInteractor
    @Inject
    constructor(private val issueSwitcher: IssuesDomainContract.Switcher)
    : Interactor<IssueEntity, Unit> {

    override fun run(request: IssueEntity) {
        issueSwitcher.switchToIssue(request)
    }
}
