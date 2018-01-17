package org.assertj.swing.fixture;

import com.intellij.ui.table.TableView;
import org.assertj.swing.core.Robot;

public class TableViewFixture extends JTableFixture {
    private final TableView<?> target;

    public TableViewFixture(Robot robot, TableView<?> target) {
        super(robot, target);

        this.target = target;
    }
}
