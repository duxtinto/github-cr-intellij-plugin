package com.duxtinto.intellij.plugin.github.codereviews.presentation.toolwindow

import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.PullRequestEntity
import javax.swing.*

interface ToolWindowContent {
    interface Reviewee {
        interface View {
            val content: JComponent
        }
        interface Presenter {
            fun setView(view: Reviewee.View)
            fun presentPullRequests(pullRequests: List<PullRequestEntity>)
        }
    }

    interface Reviewer {
        interface View {
            val content: JComponent
        }
        interface Presenter {
            fun setView(view: Reviewer.View)
        }
    }

    interface Model

    interface Presenter {
        fun displayToolWindow()
    }
}
