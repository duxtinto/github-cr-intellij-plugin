package com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.issues

import com.duxtinto.intellij.plugin.github.codereviews.di.scopes.ProjectScoped
import com.duxtinto.intellij.plugin.github.codereviews.domain.Interactor
import com.duxtinto.intellij.plugin.github.codereviews.domain.issues.IssueEntity
import com.duxtinto.intellij.plugin.github.codereviews.domain.issues.IssuesDomainContract
import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.PullRequestDomainContract
import javax.inject.Inject

@ProjectScoped
class GetAllClosableByInteractor
    @Inject
    constructor(private val descriptionParser: PullRequestDomainContract.DescriptionParser,
                private val issueFetcher: IssuesDomainContract.Fetcher)
    : Interactor<GetAllClosableByRequest, List<IssueEntity>> {

    private lateinit var request: GetAllClosableByRequest

    override fun run(request: GetAllClosableByRequest): List<IssueEntity> {
        this.request = request
        return transformToEntityList(parseForClosableIssues())
    }

    private fun transformToEntityList(closableIssueMap: Collection<Long>): List<IssueEntity> {
        return closableIssueMap
                .mapNotNull { id ->
                    issueFetcher.fetchIssueById(id)
                }
    }

    private fun parseForClosableIssues(): Collection<Long> {
        return descriptionParser
                .parse(request.description)
                .closableIssues
    }
}
