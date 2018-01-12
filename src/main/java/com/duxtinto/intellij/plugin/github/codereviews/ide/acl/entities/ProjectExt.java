package com.duxtinto.intellij.plugin.github.codereviews.ide.acl.entities;

import com.google.common.collect.ForwardingObject;
import com.intellij.openapi.project.Project;

public class ProjectExt extends ForwardingObject{
    private final Project delegate;

    private ProjectExt(Project project) {
        this.delegate = project;
    }

    public static ProjectExt create(Project delegate) {
        return new ProjectExt(delegate);
    }

    @Override
    public Project delegate() {
        return delegate;
    }
}
