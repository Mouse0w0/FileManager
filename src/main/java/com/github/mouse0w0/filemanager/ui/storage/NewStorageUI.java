package com.github.mouse0w0.filemanager.ui.storage;

import com.github.mouse0w0.filemanager.storage.Storage;
import com.github.mouse0w0.filemanager.ui.MainUI;
import com.github.mouse0w0.filemanager.ui.UIHelper;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.DirectoryChooser;

import java.io.File;
import java.nio.file.Path;

public class NewStorageUI extends BorderPane {

    private final MainUI parent;

    @FXML
    private TextField name;
    @FXML
    private TextField path;

    public NewStorageUI(MainUI parent) {
        this.parent = parent;
        UIHelper.load(this, "NewStorage");
    }

    @FXML
    public void openDirectoryChooser() {
        DirectoryChooser chooser = new DirectoryChooser();
        chooser.setTitle("Choose Storage Directory");
        File file = chooser.showDialog(getScene().getWindow());
        if (file != null) {
            path.setText(file.getAbsolutePath());
        }
    }

    @FXML
    public void newStorage() {
        Storage storage = new Storage(Path.of(path.getText()));
        storage.getSettings().setName(name.getText());
        storage.saveSettings();
        UIHelper.closeWindow(this);
        parent.openStorage(storage);
    }
}
