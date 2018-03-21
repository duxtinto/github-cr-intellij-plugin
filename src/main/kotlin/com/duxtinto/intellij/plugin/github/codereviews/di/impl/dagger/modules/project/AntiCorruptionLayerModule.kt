package com.duxtinto.intellij.plugin.github.codereviews.di.impl.dagger.modules.project

import com.duxtinto.intellij.plugin.github.codereviews.di.impl.dagger.modules.project.idea.*
import com.duxtinto.intellij.plugin.github.codereviews.di.impl.dagger.modules.project.idea.actions.IdeaActionModule
import dagger.Module

@Module(includes = [
    IdeaActionModule::class,
    IdeaTaskModule::class,
    IdeaToolWindowModule::class,
    IdeaUiContentModule::class,
    IdeaGitModule::class,
    IdeaGithubModule::class
])
abstract class AntiCorruptionLayerModule