package com.duxtinto.intellij.plugin.github.codereviews.services.pullrequests;

import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.PullRequestDescriptionEntity;
import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.PullRequestDomainContract;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

import javax.inject.Inject;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class GithubDescriptionParser implements PullRequestDomainContract.DescriptionParser {
    private Pattern closingKeywordPattern = Pattern.compile("(close|closes|closed|fix|fixes|fixed|resolve|resolves|resolved)");
    private Pattern issueNumberPattern = Pattern.compile("^#(\\d*)$", Pattern.CASE_INSENSITIVE);

    @Inject
    public GithubDescriptionParser() {
    }

    @Override
    public PullRequestDescriptionEntity parse(String description) {
        Set<Long> closableIssues = new HashSet<>();
        for (Element issueLink : getClosingIssueLinksFrom(description)) {
            closableIssues.add(getIssueNumber(issueLink));
        }

        return new PullRequestDescriptionEntity(closableIssues);
    }

    private Iterable<? extends Element> getClosingIssueLinksFrom(String description) {
        return Jsoup.parseBodyFragment(description).select(".issue-keyword")
                .stream()
                .filter(this::isAValidClosingIssueElement)
                .map(Element::nextElementSibling)
                .collect(Collectors.toList());
    }

    private boolean isAValidClosingIssueElement(Element keyword) {
        return closingKeywordPattern.matcher(keyword.ownText()).find() &&
                keyword.nextElementSibling().is("a.issue-link") &&
                issueNumberPattern.matcher(keyword.nextElementSibling().html()).find();
    }

    private Long getIssueNumber(Element link) {
        Matcher matcher = issueNumberPattern.matcher(link.html());
        if (!matcher.find()) {
            throw new RuntimeException("Closable issues should have the format '#(\\d*)': " + link.html());
        }
        return Long.valueOf(matcher.group(1));
    }
}
