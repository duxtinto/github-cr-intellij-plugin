package com.duxtinto.intellij.plugin.github.codereviews.di.dagger;

import com.duxtinto.intellij.plugin.github.codereviews.di.scopes.ApplicationScoped;
import dagger.Component;

@Component
@ApplicationScoped
public interface ApplicationContainer {
    ProjectContainer projectContainer();
}
