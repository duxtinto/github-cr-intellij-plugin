package com.duxtinto.intellij.plugin.github.codereviews.ide.integration;

import com.duxtinto.intellij.plugin.github.codereviews.di.impl.dagger.ApplicationContainer;
import com.duxtinto.intellij.plugin.github.codereviews.di.impl.dagger.DaggerApplicationContainer;
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
