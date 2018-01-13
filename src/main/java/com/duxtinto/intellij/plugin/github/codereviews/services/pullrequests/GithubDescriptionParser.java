package com.duxtinto.intellij.plugin.github.codereviews.services.pullrequests;

import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.PullRequestDescriptionEntity;
import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.PullRequestDomainContract;
import org.jetbrains.annotations.NotNull;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

import java.io.UncheckedIOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class GithubDescriptionParser implements PullRequestDomainContract.DescriptionParser {
    private Pattern issueNumberPattern = Pattern.compile("^#(\\d*)$", Pattern.CASE_INSENSITIVE);

    @Override
    public PullRequestDescriptionEntity parse(String description) {
        Map<Long, URL> closableIssueNumbers = Jsoup.parseBodyFragment(description)
                .select(".issue-keyword + a.issue-link")
                .stream()
                .collect(
                        Collectors.toMap(
                                this::getIssueNumber,
                                this::getLinkAddress));

        return PullRequestDescriptionEntity.create(closableIssueNumbers);
    }

    private Long getIssueNumber(Element link) {
        Matcher matcher = issueNumberPattern.matcher(link.html());
        if (!matcher.find()) {
            throw new RuntimeException("Closable issues should have the format '#(\\d*)': " + link.html());
        }
        return Long.valueOf(matcher.group(1));
    }

    @NotNull
    private URL getLinkAddress(Element link) {
        try {
            return new URL(link.attr("href"));
        } catch (MalformedURLException e) {
            throw new UncheckedIOException(e);
        }
    }
}
