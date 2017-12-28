package com.duxtinto.github_reviews_plugin.ide.project;

import com.duxtinto.github_reviews_plugin.di.dagger.ProjectContainer;
import com.duxtinto.github_reviews_plugin.ide.application.ApplicationDIComponent;
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
