package org.assertj.swing.matcher

import com.intellij.ui.treeStructure.Tree
import org.assertj.swing.core.GenericTypeMatcher

class IdeaTreeMatcher
    : GenericTypeMatcher<Tree>(Tree::class.java) {

    override fun isMatching(component: Tree): Boolean {
        return true
    }

    override fun toString(): String {
        val format = "%s[]"
        return String.format(format, javaClass.name)
    }
}
