package com.duxtinto.intellij.plugin.github.codereviews.helpers.random.randomizers.domain

import com.duxtinto.intellij.plugin.github.codereviews.domain.User.UserEntity
import com.duxtinto.intellij.plugin.github.codereviews.helpers.random.RandomGenerator
import io.github.benas.randombeans.api.Randomizer

class UserRandomizer: Randomizer<UserEntity> {
    override fun getRandomValue(): UserEntity {
        return UserEntity(
                id = RandomGenerator.next(),
                username = RandomGenerator.next()
        )
    }
}
