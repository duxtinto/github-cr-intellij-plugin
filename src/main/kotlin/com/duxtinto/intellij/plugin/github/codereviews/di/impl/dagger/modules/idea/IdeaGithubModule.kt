package com.duxtinto.intellij.plugin.github.codereviews.di.impl.dagger.modules.idea

import com.duxtinto.intellij.plugin.github.codereviews.di.scopes.ProjectScoped
import com.duxtinto.intellij.plugin.github.codereviews.domain.DomainDataMapper
import com.duxtinto.intellij.plugin.github.codereviews.domain.User.UserEntity
import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.PullRequestEntity
import com.duxtinto.intellij.plugin.github.codereviews.ide.acl.entities.GithubConnectionExt
import com.duxtinto.intellij.plugin.github.codereviews.ide.acl.entities.ProjectExt
import com.duxtinto.intellij.plugin.github.codereviews.ide.acl.services.github.codereviews.IdeaCodeReviewerLoader
import com.duxtinto.intellij.plugin.github.codereviews.ide.acl.services.github.pullrequests.IdeaPullRequestLoader
import com.duxtinto.intellij.plugin.github.codereviews.ide.acl.services.github.users.IdeaUserLoader
import com.duxtinto.intellij.plugin.github.codereviews.net.NetContract
import com.duxtinto.intellij.plugin.github.codereviews.net.pullrequests.apiv3.PullRequestResponse
import com.duxtinto.intellij.plugin.github.codereviews.net.users.apiv3.UserResponse
import com.intellij.openapi.progress.ProgressIndicator
import dagger.Module
import dagger.Provides
import org.jetbrains.plugins.github.api.GithubConnection
import org.jetbrains.plugins.github.util.AuthLevel
import org.jetbrains.plugins.github.util.GithubAuthDataHolder
import org.jetbrains.plugins.github.util.GithubUtil
import java.io.IOException

@Module
class IdeaGithubModule {
    @Provides
    @ProjectScoped
    fun provideGithubAuthDataHolder(project: ProjectExt, progressIndicator: ProgressIndicator): GithubAuthDataHolder? {
        return try {
            // TODO: because of the exception handling here, it 'smells' to me that hidden inside a module is not the place for this call
            GithubUtil.getValidAuthDataHolderFromConfig(project, AuthLevel.LOGGED, progressIndicator)
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }

    }

    @Provides
    @ProjectScoped
    fun provideGithubConnection(authHolder: GithubAuthDataHolder?): GithubConnectionExt {
        return GithubConnectionExt.create(GithubConnection(authHolder!!.authData, true))
    }

    @Provides
    @ProjectScoped
    fun providePullRequestLoader(
            connection: GithubConnectionExt,
            mapper: @JvmSuppressWildcards DomainDataMapper<PullRequestEntity, PullRequestResponse>
    ): NetContract.PullRequest.Loader {
        return IdeaPullRequestLoader(connection, mapper)
    }

    @Provides
    @ProjectScoped
    fun provideUserLoader(
            connection: GithubConnectionExt,
            mapper: @JvmSuppressWildcards DomainDataMapper<UserEntity, UserResponse>
    ): NetContract.User.Loader {
        return IdeaUserLoader(connection, mapper)
    }

    @Provides
    @ProjectScoped
    fun provideCodeReviewReviewerLoader(
            connection: GithubConnectionExt
    ): NetContract.CodeReview.Reviewer.Loader {
        return IdeaCodeReviewerLoader(connection)
    }
}
