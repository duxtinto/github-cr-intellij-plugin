package com.duxtinto.intellij.plugin.github.codereviews.data.pullrequests

import com.duxtinto.intellij.plugin.github.codereviews.data.DataContract
import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.PullRequestEntity.State
import com.duxtinto.intellij.plugin.github.codereviews.helpers.fixtures.Fixture
import com.duxtinto.intellij.plugin.github.codereviews.net.pullrequests.apiv3.PullRequestFetcher
import com.duxtinto.intellij.plugin.github.codereviews.net.pullrequests.apiv3.PullRequestQueryParameters
import com.duxtinto.intellij.plugin.github.codereviews.net.users.apiv3.UserFetcher
import mockit.Expectations
import mockit.Injectable
import mockit.Mocked
import mockit.Tested
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class PullRequestRepositoryTest {
    @Injectable
    private lateinit var userFetcher: UserFetcher
    @Injectable
    private lateinit var pullRequestFetcher: PullRequestFetcher
    @Injectable
    private lateinit var reviewerFetcher: DataContract.CodeReview.Reviewer.Fetcher

    @Tested
    private lateinit var sut: PullRequestRepository

    @Test
    @DisplayName("get all my assigned pull requests by repository should only return my open assigned pull requests")
    fun getAllMyAssignedBy(@Mocked queryParameters: PullRequestQueryParameters) {
        // Arrange
        val myUser = Fixture.user().build()
        val meAsReviewee = Fixture.reviewee().withUsername(myUser.username).build()
        val myOpenPullRequests = Fixture.pullRequest().withState(State.OPEN).forReviewee(meAsReviewee).buildList(4)
        val somebodyElseOpenPullRequests = Fixture.pullRequest().withState(State.OPEN).buildList(6)

        object : Expectations() {
            init {
                PullRequestQueryParameters(state = PullRequestQueryParameters.State.OPEN)
                result = queryParameters

                pullRequestFetcher.fetchAllForRepository("repo-owner", "repo-name", queryParameters)
                result = myOpenPullRequests.plus(somebodyElseOpenPullRequests)

                userFetcher.fetchAuthenticated()
                result = myUser
            }
        }

        // Act
        val pullRequests = sut.getAllMyAssignedBy("repo-owner", "repo-name")

        // Assert
        assertThat(pullRequests).isEqualTo(myOpenPullRequests)
    }

    @Test
    @DisplayName("get all the pull request where I have been requested for a review")
    fun getAllMyRequestedBy(@Mocked queryParameters: PullRequestQueryParameters) {
        // Arrange
        val myUser = Fixture.user().build()
        val myAsAReviewer = Fixture.reviewer().withUsername(myUser.username).build()
        val anotherReviewer = Fixture.reviewer().build()
        val prOnlyAssignedToMe = Fixture.pullRequest().reviewedBy(myAsAReviewer).build()
        val prAssignedToMeAndAnotherReviewer = Fixture.pullRequest().reviewedBy(anotherReviewer).reviewedBy(myAsAReviewer).build()
        val anotherPullRequests = Fixture.pullRequest().buildList(3)

        object : Expectations() {
            init {
                PullRequestQueryParameters(state = PullRequestQueryParameters.State.OPEN)
                result = queryParameters

                pullRequestFetcher.fetchAllForRepository("repo-owner", "repo-name", queryParameters)
                result = listOf(prOnlyAssignedToMe).plus(prAssignedToMeAndAnotherReviewer).plus(anotherPullRequests)

                reviewerFetcher.fetchAllByPullRequest(prOnlyAssignedToMe)
                result = listOf(myAsAReviewer)

                reviewerFetcher.fetchAllByPullRequest(prAssignedToMeAndAnotherReviewer)
                result = listOf(anotherReviewer, myAsAReviewer)

                userFetcher.fetchAuthenticated()
                result = myUser
            }
        }

        // Act
        val pullRequests = sut.getAllMyRequestedBy("repo-owner", "repo-name")

        // Assert
        assertThat(pullRequests).hasSize(2)
        assertThat(pullRequests).contains(prOnlyAssignedToMe, prAssignedToMeAndAnotherReviewer)
    }
}
