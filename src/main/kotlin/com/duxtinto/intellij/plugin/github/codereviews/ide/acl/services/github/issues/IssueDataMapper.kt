package com.duxtinto.intellij.plugin.github.codereviews.ide.acl.services.github.issues

import com.duxtinto.intellij.plugin.github.codereviews.domain.DomainDataMapper
import com.duxtinto.intellij.plugin.github.codereviews.domain.issues.IssueEntity
import org.jetbrains.plugins.github.api.data.GithubIssue
import javax.inject.Inject

class IssueDataMapper
    @Inject
    constructor()
    : DomainDataMapper<IssueEntity, GithubIssue> {

    override fun toEntity(dataModel: GithubIssue): IssueEntity {
        return IssueEntity(number = dataModel.number, title = dataModel.title)
    }
}