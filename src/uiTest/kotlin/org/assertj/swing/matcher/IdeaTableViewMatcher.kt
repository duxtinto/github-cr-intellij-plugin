package org.assertj.swing.matcher

import com.intellij.ui.table.TableView
import org.assertj.swing.core.GenericTypeMatcher

class IdeaTableViewMatcher
    : GenericTypeMatcher<TableView<*>>(TableView::class.java) {

    override fun isMatching(component: TableView<*>): Boolean {
        return true
    }

    override fun toString(): String {
        val format = "%s[]"
        return String.format(format, javaClass.name)
    }
}
