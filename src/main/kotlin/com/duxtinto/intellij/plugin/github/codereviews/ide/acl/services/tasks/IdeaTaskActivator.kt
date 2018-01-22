package com.duxtinto.intellij.plugin.github.codereviews.ide.acl.services.tasks

import com.duxtinto.intellij.plugin.github.codereviews.domain.issues.IssueEntity
import com.duxtinto.intellij.plugin.github.codereviews.domain.issues.IssuesDomainContract
import com.duxtinto.intellij.plugin.github.codereviews.domain.repositories.FindGithubRepoForRootFolderInteractor
import com.duxtinto.intellij.plugin.github.codereviews.domain.repositories.GithubRepositoryEntity
import com.intellij.tasks.TaskManager

import javax.inject.Inject

class IdeaTaskActivator
    @Inject
    constructor(
            private val taskManager : TaskManager,
            private val rootRepoFinder: FindGithubRepoForRootFolderInteractor)
    : IssuesDomainContract.Switcher {

    override fun switchToIssue(issue: IssueEntity) {
        val repository: GithubRepositoryEntity = rootRepoFinder.run(null) ?: return
        val task = taskManager
                .getIssues(generateQuery(repository, issue))
                .find {
                    it.number == issue.number.toString()
                }

        if (task != null) {
            taskManager.activateTask(task, true)
        }
    }

    private fun generateQuery(repository: GithubRepositoryEntity, issue: IssueEntity): String {
        return "repo:${repository.ownerName}/${repository.name} ${issue.title} in:title"
    }
}
