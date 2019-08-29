package com.github.mouse0w0.filemanager.ui.storage;

import com.github.mouse0w0.filemanager.ui.UIHelper;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.DirectoryChooser;

public class NewStorageUI extends BorderPane {

    @FXML
    private TextField name;
    @FXML
    private TextField path;

    public NewStorageUI() {
        UIHelper.load(this, "NewStorage");
    }

    @FXML
    public void openDirectoryChooser() {
        DirectoryChooser chooser = new DirectoryChooser();
        chooser.setTitle("Choose Storage Directory");
        path.setText(chooser.showDialog(getScene().getWindow()).getAbsolutePath());
    }

    @FXML
    public void newStorage() {

    }
}
