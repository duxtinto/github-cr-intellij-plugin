package com.duxtinto.intellij.plugin.github.codereviews.helpers.fixtures

import com.duxtinto.intellij.plugin.github.codereviews.helpers.fixtures.builders.ide.GithubIssueBuilder
import com.duxtinto.intellij.plugin.github.codereviews.helpers.fixtures.builders.net.CodeReviewCommentResponseBuilder

class NetFixture {
    fun issue(): GithubIssueBuilder {
        return GithubIssueBuilder()
    }

    fun commentResponse(): CodeReviewCommentResponseBuilder {
        return CodeReviewCommentResponseBuilder()
    }
}
