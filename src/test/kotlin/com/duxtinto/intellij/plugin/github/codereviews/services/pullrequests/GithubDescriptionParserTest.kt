package com.duxtinto.intellij.plugin.github.codereviews.services.pullrequests

import mockit.Tested
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import java.net.MalformedURLException
import java.util.stream.Stream

internal class GithubDescriptionParserTest {

    @Tested
    private lateinit var sutParser: GithubDescriptionParser

    @Test
    @DisplayName("parsing a description without any 'closing issue' keywords should return an empty map")
    fun parseDescriptionWithoutClosingIssueKeywords() {
        // Act
        val (closableIssues) = sutParser.parse("This is a normal description")

        // Assert
        assertThat(closableIssues).isEmpty()
    }

    @ParameterizedTest(name = "keyword: {0}")
    @MethodSource("closingKeywordProvider")
    @DisplayName("parsing a description with a 'closing issue' keyword, should return map of just one element")
    fun parseDescriptionThatClosesAnIssue(keyword: String) {
        // Arrange
        val descriptionHtml = "<p>" +
                generateClosingIssueHtml(keyword, ISSUES_ENDPOINT, 3L) +
                "</p>"

        // Act
        val (closableIssues) = sutParser.parse(descriptionHtml)

        // Assert
        assertThat(closableIssues)
                .hasSize(1)
                .contains(3L)
    }

    @Test
    @DisplayName("parsing a description should ignore any non-'closing issue' keyword")
    fun parseDescriptionWithNonClosingKeyword() {
        // Arrange
        val descriptionHtml = "<p>" +
                generateClosingIssueHtml("destroy", ISSUES_ENDPOINT, 3L) +
                "</p>"

        // Act
        val (closableIssues) = sutParser.parse(descriptionHtml)

        // Assert
        assertThat(closableIssues).isEmpty()
    }

    @Test
    @DisplayName("parsing a description should ignore any issue link which text doesn't uses the right issue number format")
    fun parseDescriptionContainingIssueLinkWithWrongNumberFormat() {
        // Arrange
        val descriptionHtml = "<p>" +
                "<span class=\"issue-keyword\">closes</span>" +
                "<a href=\"" + ISSUES_ENDPOINT + "/3\" class=\"issue-link\">3</a>" +
                "</p>"

        // Act
        val (closableIssues) = sutParser.parse(descriptionHtml)

        // Assert
        assertThat(closableIssues).isEmpty()
    }

    @Test
    @DisplayName("parsing a description with more than one 'closing issue' keyword, should find them all")
    @Throws(MalformedURLException::class)
    fun parseDescriptionThatClosesTwoIssues() {
        // Arrange
        val descriptionHtml = "<p>" +
                generateClosingIssueHtml("closes", ISSUES_ENDPOINT, 3L) +
                " , " +
                generateClosingIssueHtml("fix", ISSUES_ENDPOINT, 2L) +
                "</p>"

        // Act
        val (closableIssues) = sutParser.parse(descriptionHtml)

        // Assert
        assertThat(closableIssues)
                .hasSize(2)
                .contains(3L, 2L)
    }

    private fun generateClosingIssueHtml(keyword: String, issuesEndpoint: String, number: Long): String {
        return "<span class=\"issue-keyword\">" + keyword + "</span>" +
                "<a href=\"" + issuesEndpoint + "/" + number + "\" class=\"issue-link\">#" + number + "</a>"
    }

    companion object {
        private val ISSUES_ENDPOINT = "https://github.com/duxtinto/dummy/issues"

        /**
         * @link https://help.github.com/articles/closing-issues-using-keywords/
         */
        @JvmStatic
        private fun closingKeywordProvider(): Stream<String> {
            return Stream.of("close", "closes", "closed", "fix", "fixes", "fixed", "resolve", "resolves", "resolved")
        }
    }
}
