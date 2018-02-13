package com.duxtinto.intellij.plugin.github.codereviews.di.impl.dagger.modules

import com.duxtinto.intellij.plugin.github.codereviews.di.impl.dagger.modules.idea.*
import com.duxtinto.intellij.plugin.github.codereviews.di.impl.dagger.modules.idea.actions.IdeaActionModule
import dagger.Module

@Module(includes = [
    IdeaActionModule::class,
    IdeaTaskModule::class,
    IdeaToolWindowModule::class,
    IdeaUiContentModule::class,
    IdeaFileAndDocumentModule::class,
    IdeaGitModule::class,
    IdeaGithubModule::class
])
class AntiCorruptionLayerModule