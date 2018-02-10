package com.duxtinto.intellij.plugin.github.codereviews.ide.acl.services.github.pullrequests

import com.duxtinto.intellij.plugin.github.codereviews.domain.DomainDataMapper
import com.duxtinto.intellij.plugin.github.codereviews.domain.issues.IssueEntity
import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.PullRequestEntity
import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.issues.GetAllClosableByInteractor
import org.jetbrains.plugins.github.api.data.GithubPullRequest
import java.net.URL
import javax.inject.Inject

class IdeaPullRequestMapper
@Inject
constructor(private val getClosableIssues: GetAllClosableByInteractor)
    : DomainDataMapper<PullRequestEntity, GithubPullRequest> {

    override fun toEntity(dataModel: GithubPullRequest): PullRequestEntity {
        return PullRequestEntity(
                number = dataModel.number,
                state = PullRequestEntity.State.fromString(dataModel.state),
                title = dataModel.title,
                closeableIssues = getClosableIssues(dataModel.bodyHtml),
                url = URL(dataModel.htmlUrl),
                head = with(dataModel.head) {
                    PullRequestEntity.Link(
                            this.label,
                            this.ref,
                            this.sha
                    )
                })
    }

    private fun getClosableIssues(pullRequestDescriptionText: String): List<IssueEntity> {
        return getClosableIssues.run(pullRequestDescriptionText)
    }
}
