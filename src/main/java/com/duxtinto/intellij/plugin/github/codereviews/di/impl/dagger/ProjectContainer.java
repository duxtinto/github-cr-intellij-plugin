package com.duxtinto.intellij.plugin.github.codereviews.di.impl.dagger;

import com.duxtinto.intellij.plugin.github.codereviews.di.impl.dagger.components.UiInjectors;
import com.duxtinto.intellij.plugin.github.codereviews.di.impl.dagger.modules.ProjectModule;
import com.duxtinto.intellij.plugin.github.codereviews.di.scopes.ProjectScoped;
import dagger.Subcomponent;

@ProjectScoped
@Subcomponent(modules = ProjectModule.class)
public interface ProjectContainer extends UiInjectors {
}
