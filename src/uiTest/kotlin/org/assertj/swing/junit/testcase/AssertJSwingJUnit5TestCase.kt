package org.assertj.swing.junit.testcase

import org.assertj.swing.dummies.DummyIdeGlassPane
import org.assertj.swing.fixture.Containers
import org.assertj.swing.fixture.FrameFixture
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import javax.swing.JComponent
import javax.swing.JFrame

/**
 * JUnit5 wrapper fot the JUnit Swing Test Case
 */
abstract class AssertJSwingJUnit5TestCase : AssertJSwingJUnitTestCase() {

    @BeforeEach
    fun setUpMethod() {
        super.setUp()
    }

    override fun onSetUp() {
    }

    @AfterEach
    fun tearDownMethod() {
        super.tearDown()
    }

    companion object {
        @BeforeAll
        fun setUpClass() {
            AssertJSwingJUnitTestCase.setUpOnce()
        }

        @AfterAll
        fun tearDownClass() {
            AssertJSwingJUnitTestCase.tearDownOnce()
        }
    }

    fun showContentInIdeaFrame(content: JComponent) : FrameFixture {
        val container = Containers.frameFixtureFor(robot(), content)
        container
                .targetCastedTo(JFrame::class.java)
                .glassPane = DummyIdeGlassPane()
        container.show()
        return container
    }
}
