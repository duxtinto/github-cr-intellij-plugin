package com.duxtinto.intellij.plugin.github.codereviews.net.users.apiv3

import com.duxtinto.intellij.plugin.github.codereviews.data.DataContract
import com.duxtinto.intellij.plugin.github.codereviews.domain.User.UserEntity
import com.duxtinto.intellij.plugin.github.codereviews.net.NetContract
import javax.inject.Inject

class UserFetcher
    @Inject
    constructor(private val apiLoader: NetContract.User.Loader)
    : DataContract.User.Fetcher {

    override fun fetchAuthenticated(): UserEntity? {
        return try { apiLoader.loadAuthenticated() } catch (e: Exception) { null }
    }
}
