package com.duxtinto.intellij.plugin.github.codereviews.ide.integration;

import com.duxtinto.intellij.plugin.github.codereviews.di.impl.dagger.ProjectContainer;
import com.duxtinto.intellij.plugin.github.codereviews.di.impl.dagger.modules.ProjectModule;
import com.duxtinto.intellij.plugin.github.codereviews.ide.acl.entities.ProjectExt;
import com.intellij.dvcs.repo.VcsRepositoryManager;
import com.intellij.openapi.components.ProjectComponent;
import com.intellij.openapi.project.Project;

public class ProjectDIComponent implements ProjectComponent {

    private final ApplicationDIComponent applicationDI;
    private final Project project;

    private ProjectContainer container;

    public ProjectDIComponent(ApplicationDIComponent applicationDI, Project project) {
        this.applicationDI = applicationDI;
        this.project = project;
    }

    @Override
    public void initComponent() {
        container = applicationDI.getContainer().projectContainer(new ProjectModule(ProjectExt.create(project)));
        project.getMessageBus().connect().subscribe(VcsRepositoryManager.VCS_REPOSITORY_MAPPING_UPDATED, container.repositoryListener());
    }

    public ProjectContainer getContainer() {
        return container;
    }
}
