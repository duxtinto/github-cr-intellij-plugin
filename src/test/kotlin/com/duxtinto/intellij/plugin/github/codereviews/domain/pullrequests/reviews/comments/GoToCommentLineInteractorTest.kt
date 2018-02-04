package com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.reviews.comments

import com.duxtinto.intellij.plugin.github.codereviews.domain.DomainContract
import com.duxtinto.intellij.plugin.github.codereviews.helpers.fixtures.Fixture
import mockit.Expectations
import mockit.Injectable
import mockit.Tested
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test

internal class GoToCommentLineInteractorTest {

    @Test
    @DisplayName("run should open the file where the file is located")
    fun runHappyPath(
            @Injectable editorDriver: DomainContract.EditorDriver,
            @Tested interactor: GoToCommentLineInteractor) {
        // Arrange
        val comment = Fixture.reviewComment().build()

        object : Expectations() {
            init {
                editorDriver.openFile(comment.filePath)
                result = true
            }
        }

        // Act
        val result = interactor.run(comment)

        // Assert
        assertThat(result).isTrue()
    }

    @Test
    @DisplayName("run, if the file doesn't exist, should return false")
    fun runFileDoesNotExist(
            @Injectable editorDriver: DomainContract.EditorDriver,
            @Tested interactor: GoToCommentLineInteractor) {
        // Arrange
        val comment = Fixture.reviewComment().build()

        object : Expectations() {
            init {
                editorDriver.openFile(comment.filePath)
                result = false
            }
        }

        // Act
        val result = interactor.run(comment)

        // Assert
        assertThat(result).isFalse()
    }
}