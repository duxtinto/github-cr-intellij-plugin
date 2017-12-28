package com.duxtinto.github_reviews_plugin.ide.application;

import com.duxtinto.github_reviews_plugin.di.dagger.ApplicationContainer;
import com.duxtinto.github_reviews_plugin.di.dagger.DaggerApplicationContainer;
import com.intellij.openapi.components.ApplicationComponent;

public class ApplicationDIComponent implements ApplicationComponent {
    private ApplicationContainer container;

    @Override
    public void initComponent() {
        container = DaggerApplicationContainer.create();
    }

    public ApplicationContainer getContainer() {
        return container;
    }
}
