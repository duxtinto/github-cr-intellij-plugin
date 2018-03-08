package com.duxtinto.intellij.plugin.github.codereviews.ide.acl.services.github.users

import com.duxtinto.intellij.plugin.github.codereviews.domain.DomainDataMapper
import com.duxtinto.intellij.plugin.github.codereviews.domain.user.UserEntity
import com.duxtinto.intellij.plugin.github.codereviews.ide.acl.entities.GithubConnectionExt
import com.duxtinto.intellij.plugin.github.codereviews.net.NetContract
import com.duxtinto.intellij.plugin.github.codereviews.net.users.apiv3.UserResponse
import org.apache.http.message.BasicHeader
import org.jetbrains.plugins.github.api.GithubApiUtil.fromJson
import javax.inject.Inject

class IdeaUserLoader
    @Inject
    constructor(
            private val connection: GithubConnectionExt,
            private val userMapper: DomainDataMapper<UserEntity, UserResponse>)
    : NetContract.User.Loader {

    companion object {
        private val V3_FULL_JSON_CONTENT = "application/vnd.github.v3.full+json"
        private val ACCEPT_HEADER = BasicHeader("Accept", V3_FULL_JSON_CONTENT)
    }

    override fun loadAuthenticated(): UserEntity {
        return userMapper.toEntity(fromJson(
                connection.delegate().getRequest(
                        "/user",
                        ACCEPT_HEADER
                ),
                UserResponse::class.java
        ))
    }
}
