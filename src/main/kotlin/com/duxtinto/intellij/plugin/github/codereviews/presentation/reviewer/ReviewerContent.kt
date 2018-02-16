package com.duxtinto.intellij.plugin.github.codereviews.presentation.reviewer

import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.PullRequestEntity
import javax.swing.JComponent

interface ReviewerContent {
    interface View {
        val content: JComponent
    }

    interface Presenter {
        fun setView(view: View)
        fun presentPullRequests(pullRequests: List<PullRequestEntity>)
    }
}
