package org.assertj.swing.junit.testcase;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

/**
 * JUnit5 wrapper fot the JUnit Swing Test Case
 */
public abstract class AssertJSwingJUnit5TestCase extends AssertJSwingJUnitTestCase {

    @BeforeAll
    public static void setUpClass() {
        AssertJSwingJUnitTestCase.setUpOnce();
    }

    @BeforeEach
    public final void setUpMethod() {
        super.setUp();
    }

    @Override
    protected void onSetUp() {
    }

    @AfterAll
    public static void tearDownClass() {
        AssertJSwingJUnitTestCase.tearDownOnce();
    }

    @AfterEach
    public final void tearDownMethod() {
        super.tearDown();
    }
}
