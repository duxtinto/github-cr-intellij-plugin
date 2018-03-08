package com.duxtinto.intellij.plugin.github.codereviews.helpers.fixtures.builders.domain

import com.duxtinto.intellij.plugin.github.codereviews.domain.user.UserEntity
import com.duxtinto.intellij.plugin.github.codereviews.helpers.fixtures.FixtureBuilder
import com.duxtinto.intellij.plugin.github.codereviews.helpers.random.RandomGenerator

class UserFixtureBuilder: FixtureBuilder<UserEntity> {
    override fun build(): UserEntity {
        return RandomGenerator.next()
    }
}
