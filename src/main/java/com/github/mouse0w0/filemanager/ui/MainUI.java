package com.github.mouse0w0.filemanager.ui;

import com.github.mouse0w0.filemanager.ui.quickdrag.QuickDragPane;
import com.github.mouse0w0.filemanager.ui.storage.NewStorageUI;
import javafx.fxml.FXML;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainUI extends BorderPane {

    private final Stage stage;

    @FXML
    private CheckMenuItem alwaysOnTop;

    public MainUI(Stage stage) {
        this.stage = stage;
        UIHelper.load(this, "Main");
        alwaysOnTop.selectedProperty().addListener((observable, oldValue, newValue) -> stage.setAlwaysOnTop(newValue));

        setCenter(new QuickDragPane());
    }

    @FXML
    public void newStorage() {
        UIHelper.showUtilityWindow(stage, "New Storage", new NewStorageUI());
    }

    @FXML
    public void openStorage() {

    }
}
