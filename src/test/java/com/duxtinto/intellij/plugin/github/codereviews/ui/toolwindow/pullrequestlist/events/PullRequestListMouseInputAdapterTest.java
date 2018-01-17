package com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow.pullrequestlist.events;

import com.duxtinto.intellij.plugin.github.codereviews.domain.pullrequests.PullRequestEntity;
import com.duxtinto.intellij.plugin.github.codereviews.ui.toolwindow.pullrequestlist.columns.ColumnInfoFactory;
import com.intellij.ide.BrowserUtil;
import com.intellij.ui.table.JBTable;
import com.intellij.ui.table.TableView;
import mockit.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.awt.event.MouseEvent;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.params.provider.EnumSource.Mode.EXCLUDE;

class PullRequestListMouseInputAdapterTest {
    @Test
    @DisplayName("setting a null table for listening should throw NullPointerException")
    void listenToNullTable() {
        assertThrows(NullPointerException.class, () -> new PullRequestListMouseInputAdapter().listenToTable(null));
    }

    @Test
    @DisplayName("setting a table for listening should register the listener to that table")
    void listenToTable(@Injectable TableView<PullRequestEntity> table) {
        PullRequestListMouseInputAdapter sut = new PullRequestListMouseInputAdapter();

        sut.listenToTable(table);

        new FullVerifications() {{
            table.addMouseListener(sut);
        }};
    }

    @Test
    @DisplayName("setting a table for listening should unregister the listener from any previous table")
    void listenToTableAfterAnotherTableHasBeenSet(
            @Injectable TableView<PullRequestEntity> registeredTable,
            @Mocked TableView<PullRequestEntity> newTable,
            @Tested PullRequestListMouseInputAdapter mouseInputAdapter) {

        mouseInputAdapter.listenToTable(newTable);

        new FullVerifications(newTable) {{
            newTable.addMouseListener(mouseInputAdapter);
        }};

        new Verifications() {{
            registeredTable.removeMouseListener(mouseInputAdapter);
        }};
    }

    @Test
    @DisplayName("on mouse pressed over an issue number cell, gh's issue page should be opened in the web browser")
    void mousePressedHappyPath(
            @Injectable PullRequestEntity pullRequest,
            @Injectable TableView<PullRequestEntity> table,
            @Tested PullRequestListMouseInputAdapter sut) {
        MouseEvent event = new MouseEvent(table, 0, 0, 0, 38, 25, 1, false, 0);

        new Expectations() {{
            table.columnAtPoint(event.getPoint()); result = ColumnInfoFactory.ColumnIndexes.NUMBER.ordinal();
            table.rowAtPoint(event.getPoint()); result = 1;
            table.getRow(1); result = pullRequest;

            pullRequest.url(); result = "https://github.com/duxtinto/dummy/issues/3";
        }};

        new Expectations(BrowserUtil.class) {{
            BrowserUtil.browse("https://github.com/duxtinto/dummy/issues/3");
        }};

        sut.mousePressed(event);

        new FullVerifications() {{}};
    }

    @Test
    @DisplayName("on mouse pressed, if the table has not been set, should do nothing")
    void mousePressedIfTableNotSet(
            @Injectable MouseEvent event,
            @Tested PullRequestListMouseInputAdapter sut) {
        sut.mousePressed(event);

        new FullVerifications() {{
        }};
    }

    @Test
    @DisplayName("on mouse pressed, if the table is not the event's originator, should do nothing")
    void mousePressedIfDifferentSource(
            @Injectable TableView<PullRequestEntity> table,
            @Tested PullRequestListMouseInputAdapter sut) {
        MouseEvent event = new MouseEvent(new JBTable(), 0, 0, 0, 0, 0, 1, false, 0);

        sut.mousePressed(event);

        new FullVerifications(table) {{
        }};
    }

    @ParameterizedTest
    @EnumSource(value = ColumnInfoFactory.ColumnIndexes.class, mode = EXCLUDE, names = {"NUMBER"})
    @DisplayName("on mouse pressed, if the event is out of an issue number cell, should do nothing")
    void mousePressedInANonIssueNumberCell(
            ColumnInfoFactory.ColumnIndexes columnIndex,
            @Mocked BrowserUtil browserUtil,
            @Injectable TableView<PullRequestEntity> table,
            @Tested PullRequestListMouseInputAdapter sut) {
        MouseEvent event = new MouseEvent(table, 0, 0, 0, 38, 25, 1, false, 0);

        new Expectations() {{
            table.columnAtPoint(event.getPoint()); result = columnIndex.ordinal();
            table.rowAtPoint(event.getPoint()); result = 1;
        }};

        sut.mousePressed(event);

        new Expectations() {{
            BrowserUtil.browse(anyString); times = 0;
        }};
    }
}