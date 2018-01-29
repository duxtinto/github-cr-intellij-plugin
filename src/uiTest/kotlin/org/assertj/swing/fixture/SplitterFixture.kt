package org.assertj.swing.fixture

import com.intellij.openapi.ui.Splitter
import org.assertj.core.api.Assertions.assertThat
import org.assertj.swing.core.Robot
import org.assertj.swing.driver.SplitterDriver
import javax.swing.JComponent

class SplitterFixture(
        robot: Robot,
        private val target: Splitter)
    : AbstractComponentFixture<SplitterFixture, Splitter, SplitterDriver>(SplitterFixture::class.java, robot, target) {
    override fun createDriver(robot: Robot): SplitterDriver {
        return SplitterDriver(robot)
    }

    fun requireIsHorizontal(): SplitterFixture {
        assertThat(target.isVertical).isFalse()
        return this
    }

    fun requireLeftComponent(component: JComponent): SplitterFixture {
        requireIsHorizontal()
        assertThat(target.firstComponent).isSameAs(component)
        return this
    }

    fun requireRightComponent(component: JComponent): SplitterFixture {
        requireIsHorizontal()
        assertThat(target.secondComponent).isSameAs(component)
        return this
    }
}