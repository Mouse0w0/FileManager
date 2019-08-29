package com.github.mouse0w0.filemanager.ui.quickdrag;

import com.github.mouse0w0.filemanager.ui.UIHelper;
import com.github.mouse0w0.filemanager.ui.cell.IkonListCell;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Window;
import org.kordamp.ikonli.Ikon;
import org.kordamp.ikonli.material.Material;

public class NewQuickDragTileUI extends BorderPane {

    @FXML
    public TextField name;
    @FXML
    public ComboBox<Ikon> icon;
    @FXML
    public ColorPicker iconColor;
    @FXML
    public ColorPicker backgroundColor;
    @FXML
    public ChoiceBox<String> receiver;

    public NewQuickDragTileUI() {
        UIHelper.load(this, "NewQuickDragTile");
        iconColor.setValue(Color.BLACK);
        backgroundColor.setValue(Color.TRANSPARENT);
        icon.setCellFactory(view -> new IkonListCell());
        icon.getItems().addAll(Material.values());
    }

    public static void show(Window parent) {
        UIHelper.showUtilityWindow(parent, "New Quick Drag Tile", new NewQuickDragTileUI());
    }

    @FXML
    public void add() {

    }

    @FXML
    public void editReceiver() {

    }
}
