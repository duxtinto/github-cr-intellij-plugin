package org.assertj.swing.fixture

import com.intellij.openapi.ui.Splitter
import org.assertj.swing.annotation.RunsInEDT
import org.assertj.swing.core.GenericTypeMatcher

@RunsInEDT
fun AbstractContainerFixture<*, *, *>.splitter(matcher: GenericTypeMatcher<out Splitter>): SplitterFixture {
    return SplitterFixture(robot(), find<Splitter>(matcher))
}