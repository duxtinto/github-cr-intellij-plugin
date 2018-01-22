package org.assertj.swing.fixture.pullrequest

import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.PullRequestEntity
import com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow.pullrequestlist.columns.ColumnInfoFactory
import org.assertj.core.api.Assertions.assertThat
import org.assertj.swing.data.TableCell
import org.assertj.swing.fixture.JTableFixture
import org.jsoup.Jsoup
import java.util.stream.Collectors

fun JTableFixture.requirePullRequestRowAt(rowIndex: Int, pullRequest: PullRequestEntity): JTableFixture {
    require(rowIndex >= 0) { "Invalid row index: $rowIndex" }

    assertThat(getPullRequestNumberAt(rowIndex))
            .isEqualTo(pullRequest.number)
    assertThat(getPullRequestNumberLink(rowIndex))
            .isEqualTo("") // So far is always empty, as the link is just used for styling purpose
    assertThat(getCloseableIssueNumbersAt(rowIndex))
            .isEqualTo(pullRequest.closeableIssues.stream().map { issue -> issue.number }.collect(Collectors.toList()))
    assertThat(getPullRequestStateAt(rowIndex))
            .isEqualTo(pullRequest.state)
    assertThat(getPullRequestTitleAt(rowIndex))
            .isEqualTo(pullRequest.title)

    return this
}

private fun JTableFixture.getCloseableIssueNumbersAt(rowIndex: Int): List<Long> {
    return valueAt(TableCell.row(rowIndex).column(ColumnInfoFactory.ColumnIndexes.ISSUE.ordinal))
            .split(",").map { issueNumber -> issueNumber.toLong() }
}

private fun JTableFixture.getPullRequestNumberAt(rowIndex: Int) : Long? {
    return Jsoup
            .parseBodyFragment(
                    valueAt(TableCell.row(rowIndex).column(ColumnInfoFactory.ColumnIndexes.NUMBER.ordinal)))
            .getElementsByTag("a").first()
            .text()
            .toLongOrNull()
}

private fun JTableFixture.getPullRequestNumberLink(rowIndex: Int) : String? {
    return Jsoup
            .parseBodyFragment(
                    valueAt(TableCell.row(rowIndex).column(ColumnInfoFactory.ColumnIndexes.NUMBER.ordinal)))
            .getElementsByTag("a").first()
            .attr("href")
}

private fun JTableFixture.getPullRequestStateAt(rowIndex: Int): PullRequestEntity.State {
    return PullRequestEntity.State.fromString(
            valueAt(TableCell.row(rowIndex).column(ColumnInfoFactory.ColumnIndexes.STATE.ordinal))
    )
}

private fun JTableFixture.getPullRequestTitleAt(rowIndex: Int): String {
    return valueAt(TableCell.row(rowIndex).column(ColumnInfoFactory.ColumnIndexes.TITLE.ordinal))
}
