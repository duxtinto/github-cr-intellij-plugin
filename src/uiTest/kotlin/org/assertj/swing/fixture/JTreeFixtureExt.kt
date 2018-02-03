package org.assertj.swing.fixture

import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.reviews.CodeReviewEntity
import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.reviews.comments.CodeReviewCommentEntity
import com.intellij.ui.treeStructure.Tree
import org.assertj.core.api.Assertions.assertThat

fun JTreeFixture.requireEmptyText(text: String): JTreeFixture {
    val tree : Tree = target() as Tree

    with(tree) {
        assertThat(emptyText.text).isEqualToIgnoringCase(text)
    }

    return this
}

fun JTreeFixture.requireCodeReviewNode(review: CodeReviewEntity) {
    node(getCodeReviewNodePath(review))
}

private fun getCodeReviewNodePath(review: CodeReviewEntity) =
        "Code Reviews for PR #${review.pull_request_id}/${review.reviewer.username} [${review.state}]"

fun JTreeFixture.requireReviewCommentNode(review: CodeReviewEntity, comment: CodeReviewCommentEntity) {
    node("${getCodeReviewNodePath(review)}/${comment.body}")
}

