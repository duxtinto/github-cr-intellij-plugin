package com.duxtinto.intellij.plugin.github.codereviews.di.impl.dagger.modules

import com.duxtinto.intellij.plugin.github.codereviews.data.DataContract
import com.duxtinto.intellij.plugin.github.codereviews.domain.DomainDataMapper
import com.duxtinto.intellij.plugin.github.codereviews.domain.User.UserEntity
import com.duxtinto.intellij.plugin.github.codereviews.net.users.apiv3.UserFetcher
import com.duxtinto.intellij.plugin.github.codereviews.net.users.apiv3.UserMapper
import com.duxtinto.intellij.plugin.github.codereviews.net.users.apiv3.UserResponse
import dagger.Binds
import dagger.Module

@Module
abstract class UserModule {
    @Binds
    abstract fun provideFetcher(fetcher: UserFetcher): DataContract.User.Fetcher

    @Binds
    abstract fun provideResponseMapper(mapper: UserMapper): DomainDataMapper<UserEntity, UserResponse>
}
