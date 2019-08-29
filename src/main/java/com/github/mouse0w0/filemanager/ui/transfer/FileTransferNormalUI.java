package com.github.mouse0w0.filemanager.ui.transfer;

import com.github.mouse0w0.filemanager.storage.Storage;
import com.github.mouse0w0.filemanager.ui.UIHelper;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.DirectoryChooser;

import java.io.File;

public class FileTransferNormalUI extends BorderPane {

    private final Storage storage;

    @FXML
    public TextField path;

    public FileTransferNormalUI(Storage storage) {
        this.storage = storage;
        UIHelper.load(this, "FileTransferNormal");
    }

    @FXML
    public void openDirectoryChooser() {
        DirectoryChooser chooser = new DirectoryChooser();
        chooser.setInitialDirectory(storage.getPath().toFile());
        chooser.setTitle("Choose Target Directory");
        File file = chooser.showDialog(getScene().getWindow());
        if (file != null) {
            path.setText(file.getAbsolutePath());
        }
    }

    @FXML
    public void save() {
        UIHelper.closeWindow(this);
    }
}
