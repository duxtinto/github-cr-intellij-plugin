package com.duxtinto.intellij.plugin.github.codereviews.services.pullrequests

import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.PullRequestDescriptionEntity
import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.PullRequestDomainContract
import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import java.util.regex.Pattern
import javax.inject.Inject

class GithubDescriptionParser
    @Inject
    constructor()
    : PullRequestDomainContract.DescriptionParser {
    private val closingKeywordPattern = Pattern.compile("(close|closes|closed|fix|fixes|fixed|resolve|resolves|resolved)")
    private val issueNumberPattern = Pattern.compile("^#(\\d*)$", Pattern.CASE_INSENSITIVE)

    override fun parse(description: String): PullRequestDescriptionEntity {
        val closableIssues = getClosingIssueLinksFrom(description)
                .map { getIssueNumber(it) }
                .toSet()

        return PullRequestDescriptionEntity(closableIssues)
    }

    private fun getClosingIssueLinksFrom(description: String): Iterable<Element> {
        return Jsoup
                .parseBodyFragment(description)
                .select(".issue-keyword")
                .filter { isAValidClosingIssueElement(it) }
                .map { it.nextElementSibling() }
    }

    private fun isAValidClosingIssueElement(keyword: Element): Boolean {
        return closingKeywordPattern.matcher(keyword.ownText()).find() &&
                keyword.nextElementSibling().`is`("a.issue-link") &&
                issueNumberPattern.matcher(keyword.nextElementSibling().html()).find()
    }

    private fun getIssueNumber(link: Element): Long {
        val matcher = issueNumberPattern.matcher(link.html())
        if (!matcher.find()) {
            throw RuntimeException("Closable issues should have the format '#(\\d*)': " + link.html())
        }
        return matcher.group(1).toLong()
    }
}
