package com.duxtinto.intellij.plugin.github.codereviews.helpers.fixtures

import com.duxtinto.intellij.plugin.github.codereviews.helpers.fixtures.builders.ide.GithubIssueBuilder

class IdeFixture {
    fun issue(): GithubIssueBuilder {
        return GithubIssueBuilder()
    }
}
