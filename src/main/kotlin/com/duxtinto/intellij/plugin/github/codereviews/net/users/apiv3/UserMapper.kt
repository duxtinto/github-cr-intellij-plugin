package com.duxtinto.intellij.plugin.github.codereviews.net.users.apiv3

import com.duxtinto.intellij.plugin.github.codereviews.domain.DomainDataMapper
import com.duxtinto.intellij.plugin.github.codereviews.domain.User.UserEntity
import javax.inject.Inject

class UserMapper
    @Inject
    constructor()
    : DomainDataMapper<UserEntity, UserResponse> {
    override fun toEntity(dataModel: UserResponse): UserEntity {
        return UserEntity(
                id = dataModel.id,
                username = dataModel.login
        )
    }
}
