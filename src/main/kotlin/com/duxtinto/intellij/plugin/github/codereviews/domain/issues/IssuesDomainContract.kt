package com.duxtinto.intellij.plugin.github.codereviews.domain.issues

interface IssuesDomainContract {
    interface Switcher {
        fun switchToIssue(issue: IssueEntity)
    }

    interface Fetcher {
        fun fetchIssueById(id: Long): IssueEntity?
    }
}
