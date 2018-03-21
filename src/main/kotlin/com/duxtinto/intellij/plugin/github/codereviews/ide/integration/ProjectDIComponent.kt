package com.duxtinto.intellij.plugin.github.codereviews.ide.integration

import com.duxtinto.intellij.plugin.github.codereviews.di.impl.dagger.ProjectContainer
import com.duxtinto.intellij.plugin.github.codereviews.di.impl.dagger.modules.project.ProjectModule
import com.duxtinto.intellij.plugin.github.codereviews.ide.acl.entities.ProjectExt
import com.intellij.dvcs.repo.VcsRepositoryManager
import com.intellij.dvcs.repo.VcsRepositoryMappingListener
import com.intellij.openapi.components.ProjectComponent
import com.intellij.openapi.project.Project

class ProjectDIComponent(
        private val applicationDI: ApplicationDIComponent,
        private val project: Project)
    : ProjectComponent {
    lateinit var container: ProjectContainer
        private set

    override fun getComponentName(): String {
        return "GithubReviewsProjectComponent"
    }

    override fun initComponent() {
        container = applicationDI.container.projectContainer(ProjectModule(ProjectExt(project)))
        project.messageBus.connect().subscribe<VcsRepositoryMappingListener>(VcsRepositoryManager.VCS_REPOSITORY_MAPPING_UPDATED, container.repositoryListener())
    }

    override fun disposeComponent() {
    }

    override fun projectClosed() {
    }

    override fun projectOpened() {
    }
}
