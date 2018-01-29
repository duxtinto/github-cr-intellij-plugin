package org.assertj.swing.fixture

import com.intellij.ui.treeStructure.Tree
import org.assertj.core.api.Assertions.assertThat

fun JTreeFixture.requireEmptyText(text: String): JTreeFixture {
    val tree : Tree = target() as Tree

    with(tree) {
        assertThat(emptyText.text).isEqualToIgnoringCase(text)
    }

    return this
}
