package com.duxtinto.intellij.plugin.github.codereviews.ide.integration

import com.duxtinto.intellij.plugin.github.codereviews.di.impl.dagger.ApplicationContainer
import com.duxtinto.intellij.plugin.github.codereviews.di.impl.dagger.DaggerApplicationContainer
import com.intellij.openapi.components.ApplicationComponent

class ApplicationDIComponent : ApplicationComponent {
    lateinit var container: ApplicationContainer
        private set

    override fun initComponent() {
        container = DaggerApplicationContainer.create()
    }
}
