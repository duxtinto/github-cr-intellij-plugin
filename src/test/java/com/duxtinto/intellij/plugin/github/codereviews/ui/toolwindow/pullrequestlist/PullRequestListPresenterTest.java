package com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow.pullrequestlist;

import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.PullRequestEntity;
import com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow.pullrequestlist.columns.NumberColumnInfo;
import com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow.pullrequestlist.columns.TitleColumnInfo;
import com.intellij.util.ui.ColumnInfo;
import mockit.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

class PullRequestListPresenterTest {

    @Injectable
    @Named("default")
    private ColumnInfo[] columnInfos = new ColumnInfo[] {
            new NumberColumnInfo(),
            new TitleColumnInfo(),
    };

    @Test
    @DisplayName("if null column info is passed to constructor, it should throw an exception")
    void constructorIfNullColumnInfos() {
        assertThrows(NullPointerException.class, () -> new PullRequestListPresenter(null));
    }

    @Test
    @DisplayName("if columns info is passed to constructor, it should be passed to the model")
    void constructorHappyPath() {
        new Expectations() {{
            new PullRequestListModel(columnInfos);
        }};

        new PullRequestListPresenter(columnInfos);

        new Verifications() {{
            new PullRequestListModel(columnInfos); times = 1;
        }};
    }

    @Test
    @DisplayName("display pull request should update the model and pass it to the view for rendering")
    void displayPullRequestsHappyPath(
            @Mocked PullRequestListModel model,
            @Injectable PullRequestList.View view) {

        new Expectations() {{
            new PullRequestListModel(columnInfos);
            result = model;
        }};

        List<PullRequestEntity> pullRequests = new ArrayList<>();
        PullRequestListPresenter presenter = new PullRequestListPresenter(columnInfos);
        presenter.setView(view);

        presenter.displayPullRequests(pullRequests);

        new VerificationsInOrder() {{
            model.setPullRequests(pullRequests);
            view.render(model);
        }};
    }

    @Test
    @DisplayName("display pull request, if view is not set, should just update the model")
    void displayPullRequestsIfViewNotSet(@Mocked PullRequestListModel model) {
        new Expectations() {{
            new PullRequestListModel(columnInfos); result = model;
        }};

        List<PullRequestEntity> pullRequests = new ArrayList<>();
        PullRequestListPresenter presenter = new PullRequestListPresenter(columnInfos);

        presenter.displayPullRequests(pullRequests);

        new FullVerifications() {{
            model.setPullRequests(pullRequests);
        }};
    }

    @Test
    @DisplayName("setting view, if null is passed, should throw an exception")
    void setViewIfNull(@Tested PullRequestListPresenter presenter) {
        assertThrows(NullPointerException.class, () -> presenter.setView(null));
    }
}
