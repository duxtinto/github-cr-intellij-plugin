package com.duxtinto.intellij.plugin.github.codereviews.di.impl.dagger.modules

import com.duxtinto.intellij.plugin.github.codereviews.di.impl.dagger.modules.idea.*
import dagger.Module

@Module(includes = [
    IdeaTaskModule::class,
    IdeaToolWindowModule::class,
    IdeaUiContentModule::class,
    IdeaFileAndDocumentModule::class,
    IdeaGitModule::class
])
class AntiCorruptionLayerModule