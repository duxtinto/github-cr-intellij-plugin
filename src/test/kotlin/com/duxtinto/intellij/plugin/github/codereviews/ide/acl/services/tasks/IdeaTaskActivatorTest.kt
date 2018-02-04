package com.duxtinto.intellij.plugin.github.codereviews.ide.acl.services.tasks

import com.duxtinto.intellij.plugin.github.codereviews.domain.issues.IssueEntity
import com.duxtinto.intellij.plugin.github.codereviews.domain.repositories.FindGithubRepoForRootFolderInteractor
import com.duxtinto.intellij.plugin.github.codereviews.domain.repositories.GithubRepositoryEntity
import com.duxtinto.intellij.plugin.github.codereviews.helpers.fixtures.Fixture
import com.intellij.tasks.Task
import com.intellij.tasks.TaskManager
import mockit.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class IdeaTaskActivatorTest {
    @Injectable
    lateinit var taskManager: TaskManager

    @Injectable
    lateinit var rootRepoFinder: FindGithubRepoForRootFolderInteractor

    @Tested
    lateinit var sut: IdeaTaskActivator

    @Test
    @DisplayName("switch to issue, if issue is found in repository, should set it as the active task")
    fun switchToIssueHappyPath(@Mocked issueTask: Task) {
        // Arrange
        val issue = Fixture.issue().build()
        val repository = Fixture.repository().build()

        object : Expectations() {
            init {
                rootRepoFinder.run(Unit)
                result = repository

                issueTask.number
                result = issue.number

                taskManager.getIssues(with(expectedQuery(repository, issue)))
                result = listOf(issueTask)
            }
        }

        // Act
        sut.switchToIssue(issue)

        // Assert
        object : Verifications() {
            init {
                taskManager.activateTask(issueTask, true)
            }
        }
    }

    private fun expectedQuery(repository: GithubRepositoryEntity, issue: IssueEntity): Delegate<String> {
        return object : Delegate<String> {
            fun check(query: String): Boolean {
                return query.contains("in:title", true) &&
                        query.contains(issue.title, true) &&
                        query.contains("repo:${repository.ownerName}/${repository.name}", true)
            }
        }
    }

    @Test
    @DisplayName("switch to issue, if no github repo has been set for the root folder, should do nothing")
    fun switchToIssueIfNoGithubRepo() {
        // Arrange
        val issue = Fixture.issue().build()

        object : Expectations() {
            init {
                rootRepoFinder.run(Unit)
                result = null
            }
        }

        // Act
        sut.switchToIssue(issue)

        // Assert
        object : FullVerifications(taskManager) {
            init {}
        }
    }

    @Test
    @DisplayName("switch to issue, if issue is not found in the repository, should do nothing")
    fun switchToIssueIfIssueNotFound() {
        // Arrange
        val issue = Fixture.issue().build()
        val repository = Fixture.repository().build()

        object : Expectations() {
            init {
                rootRepoFinder.run(Unit)
                result = repository

                taskManager.getIssues(anyString)
                result = listOf<IssueEntity>()
            }
        }

        // Act
        sut.switchToIssue(issue)

        // Assert
        object : FullVerifications(taskManager) {
            init {}
        }
    }
}