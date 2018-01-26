package com.duxtinto.intellij.plugin.github.codereviews.helpers.fixtures.builders.ide

import com.duxtinto.intellij.plugin.github.codereviews.helpers.fixtures.FixtureBuilder
import com.duxtinto.intellij.plugin.github.codereviews.helpers.random.RandomGenerator
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import org.jetbrains.plugins.github.api.data.GithubIssue

class GithubIssueBuilder: FixtureBuilder<GithubIssue> {
    private var number: Long = RandomGenerator.nextLong()
    private var title: String = RandomGenerator.nextObject(String::class)

    companion object {
        val gson = GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
                .create()

        val jsonParser = JsonParser()
    }

    fun withTitle(title: String): GithubIssueBuilder {
        this.title = title
        return this
    }

    fun withNumber(number: Long): GithubIssueBuilder {
        this.number = number
        return this
    }

    override fun build(): GithubIssue {
        val stringIssue = "{ number:\"$number\", title:\"$title\" }"
        return gson.fromJson(jsonParser.parse(stringIssue), GithubIssue::class.java)
    }
}
