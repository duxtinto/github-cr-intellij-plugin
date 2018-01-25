package org.assertj.swing.matcher

import com.intellij.openapi.ui.Splitter
import org.assertj.swing.core.GenericTypeMatcher

class SplitterMatcher
    : GenericTypeMatcher<Splitter>(Splitter::class.java) {

    override fun isMatching(component: Splitter): Boolean {
        return true
    }

    override fun toString(): String {
        val format = "%s[]"
        return String.format(format, javaClass.name)
    }
}
