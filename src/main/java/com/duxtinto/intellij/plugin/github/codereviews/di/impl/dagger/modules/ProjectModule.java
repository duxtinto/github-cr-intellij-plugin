package com.duxtinto.intellij.plugin.github.codereviews.di.impl.dagger.modules;

import com.duxtinto.intellij.plugin.github.codereviews.di.scopes.ProjectScoped;
import com.duxtinto.intellij.plugin.github.codereviews.ide.acl.entities.ProjectExt;
import com.intellij.openapi.progress.EmptyProgressIndicator;
import com.intellij.openapi.progress.ProgressIndicator;
import dagger.Module;
import dagger.Provides;

@Module(includes = {AuthModule.class, ReposModule.class, PullRequestModule.class, UiModule.class})
public class ProjectModule {
    private final ProjectExt project;

    public ProjectModule(ProjectExt project) {
        this.project = project;
    }

    @Provides
    @ProjectScoped
    public ProjectExt provideProject() {
        return this.project;
    }

    @Provides
    @ProjectScoped
    public ProgressIndicator provideProgressIndicator() {
        return new EmptyProgressIndicator();
    }
}
