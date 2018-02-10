package com.duxtinto.intellij.plugin.github.codereviews.presentation.toolwindow

import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.impl.ToolWindowImpl
import mockit.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class ToolWindowFactoryTest {
    @Injectable
    private lateinit var GH_Reviews: ToolWindowImpl

    @Injectable
    private lateinit var presenter: ToolWindowContent.Presenter

    @Tested
    private lateinit var factory: ToolWindowFactory

    @Test
    @DisplayName("create tool window content for the 'GH_Reviews' tool windo")
    fun createToolWindowContent(@Injectable project: Project) {
        // Act
        factory.createToolWindowContent(project, GH_Reviews)

        // Assert
        object : Verifications() {
            init {
                presenter.displayToolWindow()
            }
        }
    }

    @Test
    @DisplayName("create tool window content, for a window different from 'GH_Reviews', shoudl do nothing")
    fun createToolWindowContentForNonGHReviewsToolWindow(
            @Injectable project: Project,
            @Mocked anotherToolWindow: ToolWindowImpl) {
        // Arrange
        object : Expectations() {
            init {
                anotherToolWindow.id
                result = 54
            }
        }

        // Act
        factory.createToolWindowContent(project, anotherToolWindow)

        // Assert
        object : Verifications() {
            init {
                presenter.displayToolWindow()
                times = 0
            }
        }
    }
}