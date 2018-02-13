package com.duxtinto.intellij.plugin.github.codereviews.helpers.fixtures.builders.net

import com.duxtinto.intellij.plugin.github.codereviews.helpers.fixtures.FixtureBuilder
import com.duxtinto.intellij.plugin.github.codereviews.helpers.random.RandomGenerator
import com.duxtinto.intellij.plugin.github.codereviews.net.users.apiv3.UserResponse

class UserResponseBuilder: FixtureBuilder<UserResponse> {
    override fun build(): UserResponse {
        return RandomGenerator.next()
    }
}
