package com.duxtinto.intellij.plugin.github.codereviews.ide.acl.entities

import com.intellij.openapi.project.Project

class ProjectExt internal constructor(private val ideaProject: Project) : Project by ideaProject
