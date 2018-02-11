package com.duxtinto.intellij.plugin.github.codereviews.presentation.reviewee

import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.PullRequestEntity
import com.duxtinto.intellij.plugin.github.codereviews.presentation.Toolbar as BaseToolbar
import javax.swing.JComponent

interface RevieweeContent {
    interface View {
        val content: JComponent
    }

    interface Presenter {
        fun setView(view: View)
        fun presentPullRequests(pullRequests: List<PullRequestEntity>)
    }

    interface Toolbar: BaseToolbar
}
