package com.duxtinto.intellij.plugin.github.codereviews.domain.repositories.git

import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.PullRequestEntity
import com.duxtinto.intellij.plugin.github.codereviews.domain.repositories.RepositoriesDomainContract
import com.duxtinto.intellij.plugin.github.codereviews.helpers.fixtures.Fixture
import com.duxtinto.intellij.plugin.github.codereviews.ide.acl.entities.git.GitRepositoryExt
import mockit.*
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class CheckoutBranchInteractorTest {

    @Injectable
    private lateinit var branchOperator: RepositoriesDomainContract.Git.BranchOperator

    @Injectable
    private lateinit var gitRepositoryFinder: RepositoriesDomainContract.Git.RepositoryFinder

    @Tested
    private lateinit var sut: CheckoutBranchInteractor

    @Test
    @DisplayName("Run should checkout the pull request branch")
    fun runHappyPath(@Injectable repository: GitRepositoryExt) {
        // Arrange
        val pullRequest = Fixture.pullRequest().build()

        object : Expectations() {
            init {
                gitRepositoryFinder.findRootRepo()
                result = repository

                repository.currentBranch?.name
                result = "another-checked-branch"
            }
        }

        // Act
        sut.run(pullRequest)

        // Assert
        object : Verifications() {
            init {
                branchOperator.checkOut(pullRequest.head?.ref!!, repository)
            }
        }
    }

    @Test
    @DisplayName("Run, if branch is same as checked out, should do nothing")
    fun runIfBranchAlreadyCheckedOut(@Injectable repository: GitRepositoryExt) {
        // Arrange
        val pullRequest = Fixture.pullRequest().build()

        object : Expectations() {
            init {
                gitRepositoryFinder.findRootRepo()
                result = repository

                repository.currentBranch?.name
                result = pullRequest.head?.ref
            }
        }

        // Act
        sut.run(pullRequest)

        // Assert
        object : FullVerifications() {
            init {}
        }
    }

    @Test
    @DisplayName("Run, if root repository doesn't exist, should fail")
    fun runIfNoRepository() {
        // Arrange
        val pullRequest = Fixture.pullRequest().build()

        object: Expectations() {
            init {
                gitRepositoryFinder.findRootRepo()
                result = null
            }
        }

        // Act
        assertThrows(KotlinNullPointerException::class.java, { sut.run(pullRequest) })
    }

    @Test
    @DisplayName("Run, if branch is null, should fail")
    fun runIfNoBranch(
            @Injectable repository: GitRepositoryExt,
            @Injectable pullRequest: PullRequestEntity) {
        // Arrange
        object: Expectations() {
            init {
                pullRequest.head
                result = null

                gitRepositoryFinder.findRootRepo()
                result = repository
            }
        }

        // Act
        assertThrows(KotlinNullPointerException::class.java, { sut.run(pullRequest) })
    }
}
