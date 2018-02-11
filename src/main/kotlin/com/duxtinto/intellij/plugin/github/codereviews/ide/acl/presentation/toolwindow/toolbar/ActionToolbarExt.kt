package com.duxtinto.intellij.plugin.github.codereviews.ide.acl.presentation.toolwindow.toolbar

import com.duxtinto.intellij.plugin.github.codereviews.presentation.Toolbar
import com.intellij.openapi.actionSystem.ActionToolbar

class ActionToolbarExt(private val actionToolbar: ActionToolbar)
    : ActionToolbar by actionToolbar, Toolbar
