package com.duxtinto.intellij.plugin.github.codereviews.net.pullrequests.apiv3

import com.duxtinto.intellij.plugin.github.codereviews.domain.DomainDataMapper
import com.duxtinto.intellij.plugin.github.codereviews.domain.issues.IssueEntity
import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.PullRequestEntity
import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.issues.GetAllClosableByInteractor
import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.reviews.CodeRevieweeEntity
import java.net.URL
import javax.inject.Inject

class PullRequestResponseMapper
@Inject
constructor(private val getClosableIssues: GetAllClosableByInteractor)
    : DomainDataMapper<PullRequestEntity, PullRequestResponse> {

    override fun toEntity(dataModel: PullRequestResponse): PullRequestEntity {
        return PullRequestEntity(
                number = dataModel.number,
                state = PullRequestEntity.State.fromString(dataModel.state),
                title = dataModel.title,
                closeableIssues = getClosableIssues(dataModel.bodyHtml ?: ""),
                url = URL(dataModel.htmlUrl),
                head = with(dataModel.head) {
                    PullRequestEntity.Link(
                            this.label,
                            this.ref,
                            this.sha
                    )
                },
                reviewee = with(dataModel.assignee) {
                    if (this != null) CodeRevieweeEntity(this.id, this.login) else null
                })
    }

    private fun getClosableIssues(pullRequestDescriptionText: String): List<IssueEntity> {
        return getClosableIssues.run(pullRequestDescriptionText)
    }
}
