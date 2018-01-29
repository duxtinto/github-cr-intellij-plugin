package org.assertj.swing.fixture

import com.intellij.openapi.wm.impl.content.TabbedContentTabLabel
import org.assertj.swing.core.Robot
import org.assertj.swing.driver.TabbedContentTabLabelDriver

class TabbedContentTabLabelFixture(
        robot: Robot,
        target: TabbedContentTabLabel)
    : AbstractComponentFixture<TabbedContentTabLabelFixture, TabbedContentTabLabel, TabbedContentTabLabelDriver>(TabbedContentTabLabelFixture::class.java, robot, target) {
    override fun createDriver(robot: Robot): TabbedContentTabLabelDriver {
        return TabbedContentTabLabelDriver(robot)
    }
}