package com.duxtinto.intellij.plugin.github.codereviews.ide.acl.presentation.toolwindow.toolbar

import com.duxtinto.intellij.plugin.github.codereviews.di.qualifiers.Reviewee
import com.duxtinto.intellij.plugin.github.codereviews.presentation.Toolbar
import com.intellij.openapi.actionSystem.ActionManager
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.DefaultActionGroup
import javax.inject.Inject

class RevieweeToolbarFactory
    @Inject
    constructor(
            private val actionManager: ActionManager,
            @Reviewee private val actions: List<AnAction>)
    : Toolbar.Factory {
    override fun create(): Toolbar {
        val mainGroup = DefaultActionGroup()
        mainGroup.addAll(actions)
        return ActionToolbarExt(actionManager.createActionToolbar("gh_reviews.RevieweeToolbar", mainGroup, true))
    }
}
