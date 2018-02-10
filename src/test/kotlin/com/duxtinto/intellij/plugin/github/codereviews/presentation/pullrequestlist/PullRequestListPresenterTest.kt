package com.duxtinto.intellij.plugin.github.codereviews.presentation.pullrequestlist

import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.PullRequestEntity
import com.duxtinto.intellij.plugin.github.codereviews.presentation.pullrequestlist.columns.NumberColumnInfo
import com.duxtinto.intellij.plugin.github.codereviews.presentation.pullrequestlist.columns.TitleColumnInfo
import com.intellij.util.ui.ColumnInfo
import mockit.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

import javax.inject.Named
import java.util.ArrayList

import org.junit.jupiter.api.Assertions.assertThrows

internal class PullRequestListPresenterTest {

    private var columnInfos = arrayOf<ColumnInfo<PullRequestEntity, *>>(NumberColumnInfo(), TitleColumnInfo())

    @Test
    @DisplayName("if columns info is passed to constructor, it should be passed to the model")
    fun constructorHappyPath() {
        object : Expectations() {
            init {
                PullRequestListModel(columnInfos)
            }
        }

        PullRequestListPresenter(columnInfos)

        object : Verifications() {
            init {
                PullRequestListModel(columnInfos)
                times = 1
            }
        }
    }

    @Test
    @DisplayName("display pull request should update the model and pass it to the view for rendering")
    fun displayPullRequestsHappyPath(
            @Mocked model: PullRequestListModel,
            @Injectable view: PullRequestList.View) {

        object : Expectations() {
            init {
                PullRequestListModel(columnInfos)
                result = model
            }
        }

        val pullRequests = ArrayList<PullRequestEntity>()
        val presenter = PullRequestListPresenter(columnInfos)
        presenter.setView(view)

        presenter.displayPullRequests(pullRequests)

        object : VerificationsInOrder() {
            init {
                model.setPullRequests(pullRequests)
                view.render(model)
            }
        }
    }

    @Test
    @DisplayName("display pull request, if view is not set, should just update the model")
    fun displayPullRequestsIfViewNotSet(@Mocked model: PullRequestListModel) {
        object : Expectations() {
            init {
                PullRequestListModel(columnInfos)
                result = model
            }
        }

        val pullRequests = ArrayList<PullRequestEntity>()
        val presenter = PullRequestListPresenter(columnInfos)

        presenter.displayPullRequests(pullRequests)

        object : FullVerifications() {
            init {
                model.setPullRequests(pullRequests)
            }
        }
    }
}
