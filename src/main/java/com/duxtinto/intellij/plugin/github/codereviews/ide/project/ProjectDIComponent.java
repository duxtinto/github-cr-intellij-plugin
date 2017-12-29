package com.duxtinto.intellij.plugin.github.codereviews.ide.project;

import com.duxtinto.intellij.plugin.github.codereviews.di.dagger.ProjectContainer;
import com.duxtinto.intellij.plugin.github.codereviews.ide.application.ApplicationDIComponent;
import com.intellij.openapi.components.ProjectComponent;

public class ProjectDIComponent implements ProjectComponent {

    private final ApplicationDIComponent applicationDI;
    private ProjectContainer container;

    public ProjectDIComponent(ApplicationDIComponent applicationDI) {
        this.applicationDI = applicationDI;
    }

    @Override
    public void initComponent() {
        container = applicationDI.getContainer().projectContainer();
    }
}
