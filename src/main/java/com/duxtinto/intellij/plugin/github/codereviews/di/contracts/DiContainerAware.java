package com.duxtinto.intellij.plugin.github.codereviews.di.contracts;

import com.duxtinto.intellij.plugin.github.codereviews.di.impl.dagger.ApplicationContainer;
import com.duxtinto.intellij.plugin.github.codereviews.di.impl.dagger.ProjectContainer;
import com.duxtinto.intellij.plugin.github.codereviews.ide.integration.ApplicationDIComponent;
import com.duxtinto.intellij.plugin.github.codereviews.ide.integration.ProjectDIComponent;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;

public interface DiContainerAware {
    default ProjectContainer getProjectContainer(@NotNull Project project) {
        return project.getComponent(ProjectDIComponent.class).getContainer();
    }

    default ApplicationContainer getGlobalContainer() {
        return ApplicationManager.getApplication().getComponent(ApplicationDIComponent.class).getContainer();
    }
}
