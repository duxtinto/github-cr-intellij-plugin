package com.duxtinto.intellij.plugin.github.codereviews.helpers.fixtures

import com.duxtinto.intellij.plugin.github.codereviews.helpers.fixtures.builders.ide.GithubIssueBuilder
import com.duxtinto.intellij.plugin.github.codereviews.helpers.fixtures.builders.net.PullRequestResponseAssigneeBuilder
import com.duxtinto.intellij.plugin.github.codereviews.helpers.fixtures.builders.net.CodeReviewCommentResponseBuilder
import com.duxtinto.intellij.plugin.github.codereviews.helpers.fixtures.builders.net.PullRequestResponseBuilder
import com.duxtinto.intellij.plugin.github.codereviews.helpers.fixtures.builders.net.UserResponseBuilder

class NetFixture {
    fun issue(): GithubIssueBuilder {
        return GithubIssueBuilder()
    }

    fun commentResponse(): CodeReviewCommentResponseBuilder {
        return CodeReviewCommentResponseBuilder()
    }

    fun pullRequestResponse(): PullRequestResponseBuilder {
        return PullRequestResponseBuilder()
    }

    fun assignee(): PullRequestResponseAssigneeBuilder {
        return PullRequestResponseAssigneeBuilder()
    }

    fun userResponse(): UserResponseBuilder {
        return UserResponseBuilder()
    }
}
