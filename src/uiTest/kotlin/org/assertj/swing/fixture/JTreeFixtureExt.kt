package org.assertj.swing.fixture

import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.reviews.CodeReviewEntity
import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.reviews.comments.CodeReviewCommentEntity
import com.intellij.ui.treeStructure.Tree
import org.assertj.core.api.Assertions.assertThat
import javax.swing.tree.DefaultMutableTreeNode

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

private fun JTreeFixture.getCodeReviewNodePath(review: CodeReviewEntity): String {
    return "${getRootNodePath()}/${review.reviewer.username} [${review.state}]"
}

private fun JTreeFixture.getRootNodePath(): String {
    return this.target().convertValueToText(
            (this.target().model.root as? DefaultMutableTreeNode)?.userObject,
            false,
            false,
            false,
            0,
            false
    )
}

fun JTreeFixture.requireReviewCommentNode(review: CodeReviewEntity, comment: CodeReviewCommentEntity) {
    node("${getCodeReviewNodePath(review)}/${comment.body}")
}

