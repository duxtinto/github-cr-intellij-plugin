package com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.issues

import com.duxtinto.intellij.plugin.github.codereviews.domain.issues.IssueEntity
import com.duxtinto.intellij.plugin.github.codereviews.domain.issues.IssuesDomainContract
import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.PullRequestDomainContract
import javax.inject.Inject

class GetAllClosableByInteractor
    @Inject
    constructor(private val descriptionParser: PullRequestDomainContract.DescriptionParser,
                private val issueFetcher: IssuesDomainContract.Fetcher)
    : ParseIssuesFromStringInteractor {

    override fun run(request: String): List<IssueEntity> {
        return transformToEntityList(parseForClosableIssues(request))
    }

    private fun transformToEntityList(closableIssueMap: Collection<Long>): List<IssueEntity> {
        return closableIssueMap
                .mapNotNull { id ->
                    issueFetcher.fetchIssueById(id)
                }
    }

    private fun parseForClosableIssues(description: String): Collection<Long> {
        return descriptionParser
                .parse(description)
                .closableIssues
    }
}
