package com.github.mouse0w0.filemanager.ui.drag;

import com.github.mouse0w0.filemanager.ui.UIHelper;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;

public class DragTileAddUI extends BorderPane {

    public DragTileAddUI() {
        UIHelper.load(this, "DragTileAdd");
    }

    public static void show(Window parent) {
        Stage stage = new Stage();
        stage.initOwner(parent);
        stage.initStyle(StageStyle.UTILITY);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Add Tile");
        stage.setScene(new Scene(new DragTileAddUI()));
        stage.showAndWait();
    }
}
