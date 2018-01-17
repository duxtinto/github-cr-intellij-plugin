package com.duxtinto.intellij.plugin.github.codereviews.di.impl.dagger.modules;

import com.duxtinto.intellij.plugin.github.codereviews.di.scopes.ProjectScoped;
import com.duxtinto.intellij.plugin.github.codereviews.events.repos.GitChangeListener;
import com.intellij.dvcs.repo.VcsRepositoryMappingListener;
import dagger.Binds;
import dagger.Module;

@Module
public abstract class EventsModule {
    @Binds
    @ProjectScoped
    public abstract VcsRepositoryMappingListener provideVcsRepositoryMappingListener(GitChangeListener changeListener);
}
