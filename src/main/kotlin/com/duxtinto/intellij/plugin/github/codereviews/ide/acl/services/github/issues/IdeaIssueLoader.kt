package com.duxtinto.intellij.plugin.github.codereviews.ide.acl.services.github.issues

import com.duxtinto.intellij.plugin.github.codereviews.domain.issues.IssueEntity
import com.duxtinto.intellij.plugin.github.codereviews.domain.repositories.GithubRepositoryEntity
import com.duxtinto.intellij.plugin.github.codereviews.ide.acl.entities.GithubConnectionExt
import com.duxtinto.intellij.plugin.github.codereviews.ide.acl.services.github.issues.IssueDataMapper as IdeaIssueDataMapper
import org.jetbrains.plugins.github.api.GithubApiUtil
import java.io.IOException
import javax.inject.Inject

class IdeaIssueLoader
    @Inject
    constructor(
            private val connection : GithubConnectionExt,
            private val ideDataMapper : IdeaIssueDataMapper) {

    @Throws(IOException::class)
    fun loadIssueById(repository: GithubRepositoryEntity, id: Long): IssueEntity? {
        return ideDataMapper.toEntity(GithubApiUtil.getIssue(connection.delegate(), repository.ownerName, repository.name, id.toString()))
    }
}