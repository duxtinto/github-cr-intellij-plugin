package com.duxtinto.intellij.plugin.github.codereviews.ide.acl.events.repositories

import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.GetAllMyAssignedForRepoInteractor
import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.GetAllMyRequestedForRepoInteractor
import com.duxtinto.intellij.plugin.github.codereviews.domain.repositories.FindGithubRepoForRootFolderInteractor
import com.duxtinto.intellij.plugin.github.codereviews.helpers.fixtures.Fixture
import com.duxtinto.intellij.plugin.github.codereviews.presentation.reviewee.RevieweeContent
import com.duxtinto.intellij.plugin.github.codereviews.presentation.reviewer.ReviewerContent
import mockit.Expectations
import mockit.FullVerifications
import mockit.Injectable
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test

internal class GitChangeListenerTest {
    @Injectable
    private lateinit var repoFinder: FindGithubRepoForRootFolderInteractor

    @Injectable
    private lateinit var assignedPullRequestInteractor: GetAllMyAssignedForRepoInteractor

    @Injectable
    private lateinit var requestedPullRequestInteractor: GetAllMyRequestedForRepoInteractor

    @Injectable
    private lateinit var  revieweePresenter: RevieweeContent.Presenter

    @Injectable
    private lateinit var  reviewerPresenter: ReviewerContent.Presenter

    private lateinit var sut: GitChangeListener

    private fun createSut() {
        sut = GitChangeListener(
                repoFinder,
                assignedPullRequestInteractor,
                requestedPullRequestInteractor,
                revieweePresenter,
                reviewerPresenter)
    }

    @Test
    fun mappingChanged() {
        // Arrange
        val repository = Fixture.repository().build()
        val assignedPullRequests = Fixture.pullRequest().buildList(6)
        val requestedPullRequests = Fixture.pullRequest().buildList(3)

        createSut()

        object : Expectations() {
            init {
                repoFinder.run(Unit)
                result = repository

                assignedPullRequestInteractor.run(repository)
                result = assignedPullRequests

                requestedPullRequestInteractor.run(repository)
                result = requestedPullRequests
            }
        }

        // Act
        sut.mappingChanged()

        // Assert
        object : FullVerifications() {
            init {
                revieweePresenter.presentPullRequests(assignedPullRequests)
                reviewerPresenter.presentPullRequests(requestedPullRequests)
            }
        }
    }

    @Test
    @DisplayName("Mapping changed, if there is no github remote for the root repository, should do nothing")
    fun mappingChangedIfNoGithubRepo() {
        // Arrange
        createSut()

        object : Expectations() {
            init {
                repoFinder.run(Unit)
                result = null
            }
        }

        // Act
        sut.mappingChanged()

        // Assert
        object : FullVerifications() {
            init {}
        }
    }
}