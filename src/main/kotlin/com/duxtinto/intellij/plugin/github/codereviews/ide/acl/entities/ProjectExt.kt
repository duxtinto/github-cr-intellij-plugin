package com.duxtinto.intellij.plugin.github.codereviews.ide.acl.entities

import com.google.common.collect.ForwardingObject
import com.intellij.openapi.project.Project

class ProjectExt private constructor(private val delegate: Project) : ForwardingObject() {

    public override fun delegate(): Project {
        return delegate
    }

    companion object {
        fun create(delegate: Project): ProjectExt {
            return ProjectExt(delegate)
        }
    }
}
