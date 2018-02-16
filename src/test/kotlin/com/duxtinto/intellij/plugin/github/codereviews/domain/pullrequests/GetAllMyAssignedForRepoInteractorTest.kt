package com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests

import com.duxtinto.intellij.plugin.github.codereviews.helpers.fixtures.Fixture
import mockit.Expectations
import mockit.Injectable
import mockit.Tested
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class GetAllMyAssignedForRepoInteractorTest {
    @Injectable
    private lateinit var pullRequestsRepository: PullRequestDomainContract.Repository

    @Tested
    private lateinit var sut: GetAllMyAssignedForRepoInteractor

    @Test
    @DisplayName("run should return all my open pull requests")
    fun runHappyPath() {
        // Arrange
        val githubRepository = Fixture.repository().build()
        val expectedPullRequests = Fixture.pullRequest().buildList(6)

        object : Expectations() {
            init {
                pullRequestsRepository.getAllMyAssignedBy(githubRepository.ownerName, githubRepository.name)
                result = expectedPullRequests
            }
        }

        // Act
        val myOpenPullRequests = sut.run(githubRepository)

        // Assert
        assertThat(myOpenPullRequests).isEqualTo(expectedPullRequests)
    }
}