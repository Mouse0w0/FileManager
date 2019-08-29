package com.github.mouse0w0.filemanager.ui.cell;

import javafx.scene.control.ListCell;
import org.kordamp.ikonli.Ikon;
import org.kordamp.ikonli.javafx.FontIcon;

public class IkonListCell extends ListCell<Ikon> {

    @Override
    protected void updateItem(Ikon item, boolean empty) {
        super.updateItem(item, empty);
        if (empty) {
            setText(null);
            setGraphic(null);
        } else {
            setText(item.getDescription());
            setGraphic(FontIcon.of(item, 32));
        }
    }
}
