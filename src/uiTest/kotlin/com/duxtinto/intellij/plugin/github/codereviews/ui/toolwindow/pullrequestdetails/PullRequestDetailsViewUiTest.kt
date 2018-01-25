package com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow.pullrequestdetails

import org.assertj.swing.edt.GuiActionRunner
import org.assertj.swing.fixture.FrameFixture
import org.assertj.swing.fixture.requireEmptyText
import org.assertj.swing.junit.testcase.AssertJSwingJUnit5TestCase
import org.assertj.swing.matcher.IdeaTreeMatcher
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class PullRequestDetailsViewUiTest : AssertJSwingJUnit5TestCase() {

    lateinit var frame: FrameFixture

    @Test
    @DisplayName("An initialized empty view should contain an empty message")
    fun initializeEmptyTree() {
        // Arrange
        val view = GuiActionRunner.execute<PullRequestDetailsView>({ PullRequestDetailsView() })

        // Act
        frame = showContentInIdeaFrame(view.content)

        // Assert
        frame.tree(IdeaTreeMatcher())
                .requireEmptyText("Please, select a pull request above to see the here its code reviews")
    }
}

