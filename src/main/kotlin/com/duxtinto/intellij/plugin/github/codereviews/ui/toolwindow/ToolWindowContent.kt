package com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow

import javax.swing.*

interface ToolWindowContent {
    interface Reviewee {
        interface View {
            val content: JComponent
        }
        interface Presenter {
            fun setView(view: Reviewee.View)
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
