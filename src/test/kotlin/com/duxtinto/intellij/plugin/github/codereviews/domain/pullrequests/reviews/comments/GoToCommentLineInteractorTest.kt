package com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.reviews.comments

import com.duxtinto.intellij.plugin.github.codereviews.domain.DomainContract
import com.duxtinto.intellij.plugin.github.codereviews.helpers.fixtures.Fixture
import mockit.*
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class GoToCommentLineInteractorTest {

    @Test
    @DisplayName("run should open the file and go to the line")
    fun runHappyPath(
            @Mocked documentDriver: DomainContract.DocumentDriver,
            @Injectable editorDriver: DomainContract.EditorDriver,
            @Tested interactor: GoToCommentLineInteractor) {
        // Arrange
        val comment = Fixture.reviewComment().build()

        object : Expectations() {
            init {
                editorDriver.openDocument(comment.filePath)
                result = documentDriver
            }
        }

        // Act
        interactor.run(comment)

        // Assert
        object : Verifications() {
            init {
                documentDriver.goToLine(comment.lineNumber)
            }
        }
    }

    @Test
    @DisplayName("run, if the file doesn't exist, should throw exception")
    fun runFileDoesNotExist(
            @Injectable editorDriver: DomainContract.EditorDriver,
            @Tested interactor: GoToCommentLineInteractor) {
        // Arrange
        val comment = Fixture.reviewComment().build()

        object : Expectations() {
            init {
                editorDriver.openDocument(comment.filePath)
                result = KotlinNullPointerException()
            }
        }

        // Act
        assertThrows(KotlinNullPointerException::class.java, { interactor.run(comment) })
    }
}