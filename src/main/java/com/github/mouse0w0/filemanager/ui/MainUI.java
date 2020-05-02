package com.github.mouse0w0.filemanager.ui;

import com.github.mouse0w0.filemanager.storage.Storage;
import com.github.mouse0w0.filemanager.ui.quickdrag.QuickDragPane;
import com.github.mouse0w0.filemanager.ui.storage.NewStorageUI;
import javafx.fxml.FXML;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;

public class MainUI extends BorderPane {

    private final Stage stage;

    private Storage storage;

    @FXML
    private CheckMenuItem alwaysOnTop;

    public MainUI(Stage stage) {
        this.stage = stage;
        UIHelper.load(this, "Main");
        alwaysOnTop.selectedProperty().addListener((observable, oldValue, newValue) -> stage.setAlwaysOnTop(newValue));
    }

    @FXML
    public void newStorage() {
        UIHelper.showUtilityWindow(stage, "New Storage", new NewStorageUI(this));
    }

    @FXML
    public void openStorage() {
        DirectoryChooser chooser = new DirectoryChooser();
        chooser.setTitle("Choose Storage Directory");
        File file = chooser.showDialog(getScene().getWindow());
        if (file == null) return;
        openStorage(new Storage(file.toPath()));
    }

    public Storage getStorage() {
        return storage;
    }

    public void openStorage(Storage storage) {
        this.storage = storage;
        setCenter(new QuickDragPane(storage));
    }
}
