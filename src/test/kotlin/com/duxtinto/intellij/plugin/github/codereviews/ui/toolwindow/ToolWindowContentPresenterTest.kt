package com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow

import com.intellij.ui.content.Content
import com.intellij.ui.content.ContentFactory
import com.intellij.ui.content.ContentManager
import mockit.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

import javax.swing.*

internal class ToolWindowContentPresenterTest {

    @Tested
    private lateinit var presenter: ToolWindowContentPresenter

    @Injectable
    private lateinit var contentFactory: ContentFactory

    @Injectable
    private lateinit var contentManager: ContentManager

    @Test
    @DisplayName("setting the reviewee View should display it under the reviewee tab")
    fun setRevieweeView(@Injectable view: ToolWindowContent.RevieweeView, @Mocked content: Content)
    {
        // Arrange
        object : Expectations() {
            init {
                contentFactory.createContent(view.content, "Reviewee", anyBoolean)
                result = content
            }
        }

        // Act
        presenter.setRevieweeView(view)

        // Assert
        object : Verifications() {
            init {
                contentManager.addContent(content)
            }
        }
    }

    @Test
    @DisplayName("setting the reviewer View should display it under the reviewer tab")
    fun setReviewerView(@Injectable view: ToolWindowContent.ReviewerView, @Mocked content: Content)
    {
        // Arrange
        object : Expectations() {
            init {
                contentFactory.createContent(view.content, "Reviewer", anyBoolean)
                result = content
            }
        }

        // Act
        presenter.setReviewerView(view)

        // Assert
        object : Verifications() {
            init {
                contentManager.addContent(content)
            }
        }
    }
}