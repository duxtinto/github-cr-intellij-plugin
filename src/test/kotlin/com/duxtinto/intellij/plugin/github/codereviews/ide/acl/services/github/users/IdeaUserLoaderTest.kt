package com.duxtinto.intellij.plugin.github.codereviews.ide.acl.services.github.users

import com.duxtinto.intellij.plugin.github.codereviews.domain.DomainDataMapper
import com.duxtinto.intellij.plugin.github.codereviews.domain.User.UserEntity
import com.duxtinto.intellij.plugin.github.codereviews.helpers.fixtures.Fixture
import com.duxtinto.intellij.plugin.github.codereviews.ide.acl.entities.GithubConnectionExt
import com.duxtinto.intellij.plugin.github.codereviews.net.users.apiv3.UserResponse
import com.google.gson.JsonObject
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.mock
import mockit.Expectations
import mockit.Injectable
import mockit.Mocked
import mockit.Tested
import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.plugins.github.api.GithubApiUtil
import org.jetbrains.plugins.github.api.GithubConnection
import org.junit.jupiter.api.Test

internal class IdeaUserLoaderTest {
    @Injectable
    private lateinit var connection: GithubConnectionExt

    @Injectable
    private lateinit var userMapper: DomainDataMapper<UserEntity, UserResponse>

    @Tested
    private lateinit var sut: IdeaUserLoader

    @Test
    fun loadAuthenticated(@Mocked ideaGithubApi: GithubApiUtil) {
        // Arrange
        val expectedUser = Fixture.user().build()
        val userResponse = Fixture.net().userResponse().build()
        val jsonUserResponse = JsonObject()

        object : Expectations() {
            init {
                connection.delegate()
                result = mock<GithubConnection> {
                    on { getRequest(eq("/user"), any()) } doReturn jsonUserResponse
                }

                GithubApiUtil.fromJson(jsonUserResponse, UserResponse::class.java)
                result = userResponse

                userMapper.toEntity(userResponse)
                result = expectedUser
            }
        }

        // Act
        val user = sut.loadAuthenticated()

        // Assert
        assertThat(user).isSameAs(expectedUser)
    }
}