package com.duxtinto.intellij.plugin.github.codereviews.helpers.random.randomizers.net

import com.duxtinto.intellij.plugin.github.codereviews.helpers.random.RandomGenerator
import com.duxtinto.intellij.plugin.github.codereviews.net.users.apiv3.UserResponse
import io.github.benas.randombeans.api.Randomizer

class UserResponseRandomizer : Randomizer<UserResponse> {
    override fun getRandomValue(): UserResponse {
        return UserResponse(
                id = RandomGenerator.next(),
                login = RandomGenerator.next()
        )
    }
}
