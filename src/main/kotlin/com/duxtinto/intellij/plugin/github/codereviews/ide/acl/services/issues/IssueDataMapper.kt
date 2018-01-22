package com.duxtinto.intellij.plugin.github.codereviews.ide.acl.services.issues

import com.duxtinto.intellij.plugin.github.codereviews.domain.DomainDataMapper
import com.duxtinto.intellij.plugin.github.codereviews.domain.issues.IssueEntity
import org.jetbrains.plugins.github.api.data.GithubIssue
import javax.inject.Inject

class IssueDataMapper
    @Inject
    constructor()
    : DomainDataMapper<IssueEntity, GithubIssue> {

    override fun toEntity(ideDataModel: GithubIssue): IssueEntity {
        return IssueEntity(number = ideDataModel.number, title = ideDataModel.title)
    }
}