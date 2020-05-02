package com.github.mouse0w0.filemanager.ui.quickdrag;

import com.github.mouse0w0.filemanager.storage.Storage;
import com.github.mouse0w0.filemanager.storage.setting.QuickDrag;
import com.github.mouse0w0.filemanager.transfer.FileTransfer;
import com.github.mouse0w0.filemanager.transfer.FileTransfers;
import com.github.mouse0w0.filemanager.ui.UIHelper;
import com.github.mouse0w0.filemanager.ui.cell.IkonListCell;
import com.google.gson.JsonElement;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.util.StringConverter;
import org.kordamp.ikonli.Ikon;
import org.kordamp.ikonli.javafx.IkonResolver;
import org.kordamp.ikonli.material.Material;

import java.util.Random;

public class NewQuickDragTileUI extends BorderPane {

    private static final Random RANDOM = new Random();

    private final QuickDragPane parent;
    private final Storage storage;

    @FXML
    private TextField name;
    @FXML
    private ComboBox<Ikon> icon;
    @FXML
    private ColorPicker iconColor;
    @FXML
    private ColorPicker backgroundColor;
    @FXML
    private ChoiceBox<String> receiver;

    private JsonElement receiverSetting;

    public NewQuickDragTileUI(QuickDragPane parent, Storage storage) {
        this.parent = parent;
        this.storage = storage;
        UIHelper.load(this, "NewQuickDragTile");
        iconColor.setValue(Color.BLACK);
        backgroundColor.setValue(Color.rgb(RANDOM.nextInt(256), RANDOM.nextInt(256), RANDOM.nextInt(256)));
        icon.setConverter(new StringConverter<>() {
            @Override
            public String toString(Ikon object) {
                return object == null ? null : object.getDescription();
            }

            @Override
            public Ikon fromString(String string) {
                return IkonResolver.getInstance().resolveIkonHandler(string).resolve(string);
            }
        });
        icon.setCellFactory(view -> new IkonListCell());
        icon.getItems().addAll(Material.values());
        receiver.getItems().addAll(FileTransfers.getFactoryNames());
    }

    @FXML
    public void add() {
        FileTransfer.Factory factory = FileTransfers.getFactory(receiver.getValue());
        if (factory == null) {
            return;
        }

        var tile = new QuickDrag(name.getText(), icon.getValue(),
                iconColor.getValue(), backgroundColor.getValue(), factory.create(receiverSetting));
        storage.getSettings().quickDrags.quickDrag.add(tile);
        storage.saveSettings();
        parent.refreshQuickDragTile();
        UIHelper.closeWindow(this);
    }

    @FXML
    public void editReceiver() {
        FileTransfer.Factory factory = FileTransfers.getFactory(receiver.getValue());
        if (factory == null) {
            return;
        }

        Parent settingUI = factory.createSettingUI(storage, receiverSetting);
        UIHelper.showUtilityWindow(this, "Edit Receiver Setting", settingUI);
        receiverSetting = factory.resolveSettingUI(storage, settingUI);
    }
}
