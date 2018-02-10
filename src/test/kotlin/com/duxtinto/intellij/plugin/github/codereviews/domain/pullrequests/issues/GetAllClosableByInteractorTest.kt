package com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.issues

import com.duxtinto.intellij.plugin.github.codereviews.domain.issues.IssueEntity
import com.duxtinto.intellij.plugin.github.codereviews.domain.issues.IssuesDomainContract
import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.PullRequestDomainContract
import com.duxtinto.intellij.plugin.github.codereviews.helpers.fixtures.Fixture
import mockit.Expectations
import mockit.Injectable
import mockit.Tested
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class GetAllClosableByInteractorTest {
    @Injectable
    lateinit var descriptionParser: PullRequestDomainContract.DescriptionParser

    @Injectable
    lateinit var issueFetcher: IssuesDomainContract.Fetcher

    @Tested
    lateinit var interactor: GetAllClosableByInteractor

    @Test
    @DisplayName("run, if the description does not contain closable issues, should return empty list")
    fun runWithoutClosableIssues() {
        // Arrange
        val description = "A description without closing issues"

        object : Expectations() {
            init {
                descriptionParser.parse(description)
                result = Fixture.pullRequestDescription().build()
            }
        }

        // Act
        val closableIssues = interactor.run(description)

        // Assert
        assertThat(closableIssues).isEmpty()
    }

    @Test
    @DisplayName("run, if the description contains closable issues, should return empty list")
    fun runWithClosableIssues() {
        // Arrange
        val descriptionText = "A description that closes #1 and fixes #2"
        val description = Fixture
                .pullRequestDescription()
                .thatCloses(setOf(1, 2)).
                build()
        val issueNoOne = IssueEntity(1)
        val issueNoTwo = IssueEntity(2)

        object : Expectations() {
            init {
                descriptionParser.parse(descriptionText)
                result = description
                issueFetcher.fetchIssueById(1)
                result = issueNoOne
                issueFetcher.fetchIssueById(2)
                result = issueNoTwo
            }
        }

        // Act
        val result = interactor.run(descriptionText)

        // Assert
        assertThat(result)
                .hasSize(2)
                .contains(issueNoOne, issueNoTwo)
    }
}