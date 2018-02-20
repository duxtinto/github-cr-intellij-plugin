package com.duxtinto.intellij.plugin.github.codereviews.domain.repositories.git

import com.duxtinto.intellij.plugin.github.codereviews.domain.repositories.RepositoriesDomainContract
import com.duxtinto.intellij.plugin.github.codereviews.helpers.fixtures.Fixture
import com.duxtinto.intellij.plugin.github.codereviews.ide.acl.entities.git.GitRepositoryExt
import mockit.Expectations
import mockit.FullVerifications
import mockit.Injectable
import mockit.Tested
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class PullBranchInteractorTest {
    @Injectable
    private lateinit var branchOperator: RepositoriesDomainContract.Git.BranchOperator

    @Injectable
    private lateinit var branchFetcher: RepositoriesDomainContract.Git.Fetcher

    @Injectable
    private lateinit var gitRepositoryFinder: RepositoriesDomainContract.Git.RepositoryFinder

    @Tested
    private lateinit var sut: PullBranchInteractor

    @Test
    @DisplayName("run should fetch all remote branches and checkout the pull request commit")
    fun runHappyPath(@Injectable repository: GitRepositoryExt) {
        // Arrange
        val pullRequest = Fixture.pullRequest().build()

        object: Expectations() {
            init {
                gitRepositoryFinder.findRootRepo()
                result = repository
            }
        }

        // Act
        sut.run(pullRequest)

        // Assert
        object : FullVerifications() {
            init {
                branchFetcher.fetchAll(repository)
                branchOperator.checkOut(pullRequest.head?.sha!!, repository)
            }
        }
    }

    @Test
    @DisplayName("run, if no root repo can be found, should do nothing")
    fun runIfNoRepo() {
        // Arrange
        val pullRequest = Fixture.pullRequest().build()

        object: Expectations() {
            init {
                gitRepositoryFinder.findRootRepo()
                result = null
            }
        }

        // Act
        sut.run(pullRequest)

        // Assert
        object : FullVerifications() {
            init {}
        }
    }
}
