package com.duxtinto.intellij.plugin.github.codereviews.di.impl.dagger.modules;

import com.duxtinto.intellij.plugin.github.codereviews.di.scopes.ProjectScoped;
import com.duxtinto.intellij.plugin.github.codereviews.ide.acl.entities.GithubConnectionExt;
import com.duxtinto.intellij.plugin.github.codereviews.ide.acl.entities.ProjectExt;
import com.intellij.openapi.progress.ProgressIndicator;
import dagger.Module;
import dagger.Provides;
import org.jetbrains.plugins.github.api.GithubConnection;
import org.jetbrains.plugins.github.util.AuthLevel;
import org.jetbrains.plugins.github.util.GithubAuthDataHolder;
import org.jetbrains.plugins.github.util.GithubUtil;

import java.io.IOException;

@Module
public class AuthModule {
    @Provides
    @ProjectScoped
    public GithubAuthDataHolder provideGithubAuthDataHolder(ProjectExt project, ProgressIndicator progressIndicator) {
        try {
            // TODO: because of the exception handling here, it 'smells' to me that hidden inside a module is not the place for this call
            return GithubUtil.getValidAuthDataHolderFromConfig(project.delegate(), AuthLevel.LOGGED, progressIndicator);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Provides
    @ProjectScoped
    public GithubConnectionExt provideGithubConnection(GithubAuthDataHolder authHolder) {
        return GithubConnectionExt.create(new GithubConnection(authHolder.getAuthData(), true));
    }
}
