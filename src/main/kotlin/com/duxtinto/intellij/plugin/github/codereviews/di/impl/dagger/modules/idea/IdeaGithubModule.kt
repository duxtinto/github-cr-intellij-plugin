package com.duxtinto.intellij.plugin.github.codereviews.di.impl.dagger.modules.idea

import com.duxtinto.intellij.plugin.github.codereviews.di.scopes.ProjectScoped
import com.duxtinto.intellij.plugin.github.codereviews.domain.DomainDataMapper
import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.PullRequestEntity
import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.issues.GetAllClosableByInteractor
import com.duxtinto.intellij.plugin.github.codereviews.ide.acl.entities.GithubConnectionExt
import com.duxtinto.intellij.plugin.github.codereviews.ide.acl.entities.ProjectExt
import com.duxtinto.intellij.plugin.github.codereviews.ide.acl.services.github.pullrequests.IdeaPullRequestLoader
import com.duxtinto.intellij.plugin.github.codereviews.ide.acl.services.github.pullrequests.IdeaPullRequestMapper
import com.duxtinto.intellij.plugin.github.codereviews.net.Contract
import com.intellij.openapi.progress.ProgressIndicator
import dagger.Module
import dagger.Provides
import org.jetbrains.plugins.github.api.GithubConnection
import org.jetbrains.plugins.github.api.data.GithubPullRequest
import org.jetbrains.plugins.github.util.AuthLevel
import org.jetbrains.plugins.github.util.GithubAuthDataHolder
import org.jetbrains.plugins.github.util.GithubUtil

import java.io.IOException

@Module
class IdeaGithubModule {
    @Provides
    @ProjectScoped
    fun provideGithubAuthDataHolder(project: ProjectExt, progressIndicator: ProgressIndicator): GithubAuthDataHolder {
        try {
            // TODO: because of the exception handling here, it 'smells' to me that hidden inside a module is not the place for this call
            return GithubUtil.getValidAuthDataHolderFromConfig(project, AuthLevel.LOGGED, progressIndicator)
        } catch (e: IOException) {
            e.printStackTrace()
            throw RuntimeException(e)
        }

    }

    @Provides
    @ProjectScoped
    fun provideGithubConnection(authHolder: GithubAuthDataHolder): GithubConnectionExt {
        return GithubConnectionExt.create(GithubConnection(authHolder.authData, true))
    }

    @Provides
    fun provideGithubPullRequestMapper(interactor: GetAllClosableByInteractor): DomainDataMapper<PullRequestEntity, GithubPullRequest> {
        return IdeaPullRequestMapper(interactor)
    }

    @Provides
    @ProjectScoped
    fun providePullRequestLoader(
            connection: GithubConnectionExt,
            mapper: IdeaPullRequestMapper
    ): Contract.PullRequest.Loader {
        return IdeaPullRequestLoader(connection, mapper)
    }
}
