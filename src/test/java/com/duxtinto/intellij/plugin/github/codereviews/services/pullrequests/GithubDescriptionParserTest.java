package com.duxtinto.intellij.plugin.github.codereviews.services.pullrequests;

import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.PullRequestDescriptionEntity;
import mockit.Expectations;
import mockit.Tested;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class GithubDescriptionParserTest {

    private static final String ISSUES_ENDPOINT = "https://github.com/duxtinto/dummy/issues";

    @Tested
    private GithubDescriptionParser sutParser;

    @Test
    @DisplayName("parsing a description without any 'closing issue' keywords should return an empty map")
    void parseDescriptionWithoutClosingIssueKeywords() {
        // Act
        final PullRequestDescriptionEntity description = sutParser.parse("This is a normal description");

        // Assert
        assertThat(description.getClosableIssues()).isEmpty();
    }

    @ParameterizedTest(name = "keyword: {0}")
    @MethodSource("closingKeywordProvider")
    @DisplayName("parsing a description with a 'closing issue' keyword, should return map of just one element")
    void parseDescriptionThatClosesAnIssue(String keyword) {
        // Arrange
        String descriptionHtml = "<p>" +
                generateClosingIssueHtml(keyword, ISSUES_ENDPOINT, 3L) +
                "</p>";

        // Act
        final PullRequestDescriptionEntity description = sutParser.parse(descriptionHtml);

        // Assert
        assertThat(description.getClosableIssues())
                .hasSize(1)
                .contains(3L);
    }

    @Test
    @DisplayName("parsing a description should ignore any non-'closing issue' keyword")
    void parseDescriptionWithNonClosingKeyword() {
        // Arrange
        String descriptionHtml = "<p>" +
                generateClosingIssueHtml("destroy", ISSUES_ENDPOINT, 3L) +
                "</p>";

        // Act
        final PullRequestDescriptionEntity description = sutParser.parse(descriptionHtml);

        // Assert
        assertThat(description.getClosableIssues()).isEmpty();
    }

    @Test
    @DisplayName("parsing a description should ignore any issue link which text doesn't uses the right issue number format")
    void parseDescriptionContainingIssueLinkWithWrongNumberFormat() {
        // Arrange
        String descriptionHtml = "<p>" +
                "<span class=\"issue-keyword\">closes</span>" +
                "<a href=\""+ ISSUES_ENDPOINT + "/3\" class=\"issue-link\">3</a>" +
                "</p>";

        // Act
        final PullRequestDescriptionEntity description = sutParser.parse(descriptionHtml);

        // Assert
        assertThat(description.getClosableIssues()).isEmpty();
    }

    /**
     * @link https://help.github.com/articles/closing-issues-using-keywords/
     */
    private static Stream<String> closingKeywordProvider() {
        return Stream.of("close", "closes", "closed", "fix", "fixes", "fixed", "resolve", "resolves", "resolved");
    }

    @Test
    @DisplayName("parsing a description with more than one 'closing issue' keyword, should find them all")
    void parseDescriptionThatClosesTwoIssues() throws MalformedURLException {
        // Arrange
        String descriptionHtml = "<p>" +
                generateClosingIssueHtml("closes", ISSUES_ENDPOINT, 3L) +
                " , " +
                generateClosingIssueHtml("fix", ISSUES_ENDPOINT, 2L) +
                "</p>";

        // Act
        final PullRequestDescriptionEntity description = sutParser.parse(descriptionHtml);

        // Assert
        assertThat(description.getClosableIssues())
                .hasSize(2)
                .contains(3L, 2L);
    }

    private String generateClosingIssueHtml(String keyword, String issuesEndpoint, long number) {
        return  "<span class=\"issue-keyword\">" + keyword + "</span>" +
                "<a href=\""+ issuesEndpoint + "/" + number + "\" class=\"issue-link\">#" + number + "</a>";
    }
}
