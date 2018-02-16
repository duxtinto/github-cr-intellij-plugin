package com.duxtinto.intellij.plugin.github.codereviews.presentation.pullrequestlist

import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.PullRequestEntity
import com.intellij.util.ui.ColumnInfo
import javax.inject.Inject
import javax.inject.Named

abstract class PullRequestListPresenter
    constructor(columnInfos: Array<ColumnInfo<PullRequestEntity, *>>)
    : PullRequestList.Presenter {

    override val model: PullRequestList.Model = PullRequestListModel(columnInfos)
    private var view: PullRequestList.View? = null

    override fun displayPullRequests(pullRequests: List<PullRequestEntity>) {
        updateModel(pullRequests)
        view?.render(model)
    }

    private fun updateModel(pullRequests: List<PullRequestEntity>) {
        model.setPullRequests(pullRequests)
    }

    override fun setView(view: PullRequestList.View) {
        this.view = view
    }
}
