package com.duxtinto.intellij.plugin.github.codereviews.data.pullrequests

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
    @Test
    @DisplayName("get all my open by repository should only return my open pull requests")
    fun getAllMyOpenBy(
            @Mocked queryParameters: PullRequestQueryParameters,
            @Injectable userFetcher: UserFetcher,
            @Injectable pullRequestFetcher: PullRequestFetcher,
            @Tested sut: PullRequestRepository) {
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
        val pullRequests = sut.getAllMyOpenBy("repo-owner", "repo-name")

        // Assert
        assertThat(pullRequests).isEqualTo(myOpenPullRequests)
    }
}
