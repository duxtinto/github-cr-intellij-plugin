package com.duxtinto.intellij.plugin.github.codereviews.ide.acl.entities

import com.intellij.openapi.project.Project

class ProjectExt internal constructor(private val project: Project) : Project by project
