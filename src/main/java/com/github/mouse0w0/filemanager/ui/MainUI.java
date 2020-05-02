package com.github.mouse0w0.filemanager.ui;

import com.github.mouse0w0.filemanager.UserSettings;
import com.github.mouse0w0.filemanager.storage.Storage;
import com.github.mouse0w0.filemanager.ui.quickdrag.QuickDragPane;
import com.github.mouse0w0.filemanager.ui.storage.NewStorageUI;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;
import java.nio.file.Path;

public class MainUI extends BorderPane {

    private final Stage stage;

    private Storage storage;

    @FXML
    private Menu openRecent;

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

    @FXML
    public void onShowingFileMenu() {
        ObservableList<MenuItem> items = openRecent.getItems();
        items.clear();
        UserSettings.getInstance().getRecentOpen().forEach(path -> {
            MenuItem menuItem = new MenuItem(path);
            menuItem.setOnAction(event1 -> openStorage(new Storage(Path.of(path))));
            items.add(menuItem);
        });
    }

    public Storage getStorage() {
        return storage;
    }

    public void openStorage(Storage storage) {
        this.storage = storage;
        UserSettings.getInstance().addRecentOpen(storage.getPath());
        setCenter(new QuickDragPane(storage));
    }
}
