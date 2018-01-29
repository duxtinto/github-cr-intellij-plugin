package org.assertj.swing.fixture

import com.intellij.openapi.ui.Splitter
import com.intellij.openapi.wm.impl.content.TabbedContentTabLabel
import org.assertj.swing.annotation.RunsInEDT
import org.assertj.swing.core.GenericTypeMatcher

@RunsInEDT
fun AbstractContainerFixture<*, *, *>.splitter(matcher: GenericTypeMatcher<out Splitter>): SplitterFixture {
    return SplitterFixture(robot(), find<Splitter>(matcher))
}

@RunsInEDT
fun AbstractContainerFixture<*, *, *>.contentTab(matcher: GenericTypeMatcher<out TabbedContentTabLabel>): TabbedContentTabLabelFixture {
    return TabbedContentTabLabelFixture(robot(), find<TabbedContentTabLabel>(matcher))
}