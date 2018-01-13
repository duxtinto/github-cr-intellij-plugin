package com.duxtinto.intellij.plugin.github.codereviews.domain.repositories;

import com.google.auto.value.AutoValue;

import javax.annotation.Nonnull;

@AutoValue
public abstract class GithubRepositoryEntity {
    @Nonnull
    public abstract String name();
    @Nonnull
    public abstract String ownerName();

    public static GithubRepositoryEntity create(String name, String ownerName) {
        return new AutoValue_GithubRepositoryEntity(name, ownerName);
    }
}
