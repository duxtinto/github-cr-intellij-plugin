package com.duxtinto.intellij.plugin.github.codereviews.di.contracts

import com.duxtinto.intellij.plugin.github.codereviews.di.impl.dagger.ApplicationContainer
import com.duxtinto.intellij.plugin.github.codereviews.di.impl.dagger.ProjectContainer
import com.duxtinto.intellij.plugin.github.codereviews.ide.integration.ApplicationDIComponent
import com.duxtinto.intellij.plugin.github.codereviews.ide.integration.ProjectDIComponent
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.project.Project

interface DiContainerAware {

    val globalContainer: ApplicationContainer
        get() = ApplicationManager.getApplication().getComponent(ApplicationDIComponent::class.java).container

    fun getProjectContainer(project: Project): ProjectContainer {
        return project.getComponent(ProjectDIComponent::class.java).container
    }
}
