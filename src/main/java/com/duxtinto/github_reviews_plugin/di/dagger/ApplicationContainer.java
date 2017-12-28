package com.duxtinto.github_reviews_plugin.di.dagger;

import com.duxtinto.github_reviews_plugin.di.scopes.ApplicationScoped;
import dagger.Component;

@Component
@ApplicationScoped
public interface ApplicationContainer {
    ProjectContainer projectContainer();
}
