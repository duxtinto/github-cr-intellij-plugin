package com.duxtinto.intellij.plugin.github.codereviews.domain.issues

import com.duxtinto.intellij.plugin.github.codereviews.helpers.fixtures.Fixture
import mockit.FullVerifications
import mockit.Injectable
import mockit.Tested
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.net.MalformedURLException

internal class SwitchToIssueInteractorTest {
    @Test
    @DisplayName("run should switch the active issue")
    @Throws(MalformedURLException::class)
    fun runHappyPath(
            @Injectable issueSwitcher: IssuesDomainContract.Switcher,
            @Tested interactor: SwitchToIssueInteractor) {
        val issue = Fixture.issue().build()

        interactor.run(issue)

        object : FullVerifications() {
            init {
                issueSwitcher.switchToIssue(issue)
            }
        }
    }
}