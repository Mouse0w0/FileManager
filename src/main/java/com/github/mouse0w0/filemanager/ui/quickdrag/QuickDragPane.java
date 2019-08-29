package com.github.mouse0w0.filemanager.ui.quickdrag;

import com.github.mouse0w0.filemanager.storage.Storage;
import com.github.mouse0w0.filemanager.storage.setting.QuickDrag;
import com.github.mouse0w0.filemanager.storage.setting.QuickDragTile;
import com.github.mouse0w0.filemanager.transfer.FileTransfer;
import com.github.mouse0w0.filemanager.ui.UIHelper;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.util.StringConverter;
import org.controlsfx.control.GridCell;
import org.controlsfx.control.GridView;
import org.kordamp.ikonli.javafx.FontIcon;

import java.io.IOException;

public class QuickDragPane extends BorderPane {

    private final GridView<QuickDragTile> grid = new GridView<>();
    private final ChoiceBox<TransferMode> transferMode = new ChoiceBox<>();

    private final Storage storage;

    public QuickDragPane(Storage storage) {
        this.storage = storage;
        init();
        initGrid();
        initSetting();
    }

    private void init() {
        setPadding(new Insets(5));
    }

    private void initGrid() {
        grid.setCellFactory(view -> new Cell());
        grid.setHorizontalCellSpacing(5);
        grid.setVerticalCellSpacing(5);
        QuickDrag quickDrag = storage.getSettings().quickDrag;
        if (quickDrag != null) {
            grid.getItems().addAll(quickDrag.tiles);
        }
        setCenter(grid);
    }

    private void initSetting() {
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER_RIGHT);
        hBox.setSpacing(5);
        setBottom(hBox);

        Button addTile = new Button("Add Tile");
        addTile.setOnAction(event -> UIHelper.showUtilityWindow(this, "New Quick Drag Tile", new NewQuickDragTileUI(this, storage)));

        Text transferModeText = new Text("Transfer Mode:");

        transferMode.setConverter(new StringConverter<>() {
            @Override
            public String toString(TransferMode object) {
                return object.name();
            }

            @Override
            public TransferMode fromString(String string) {
                return TransferMode.valueOf(string);
            }
        });
        transferMode.getItems().addAll(TransferMode.COPY, TransferMode.MOVE);
        transferMode.getSelectionModel().select(TransferMode.COPY);

        hBox.getChildren().addAll(addTile, transferModeText, transferMode);
    }

    private class Cell extends GridCell<QuickDragTile> {

        public Cell() {
            setAlignment(Pos.CENTER);
            setContentDisplay(ContentDisplay.TOP);
            setOnDragOver(event -> {
                if (event.getGestureSource() != this) {
                    event.acceptTransferModes(transferMode.getSelectionModel().getSelectedItem());
                }
            });
            setOnDragDropped(event -> {
                FileTransfer receiver = getItem().getReceiver();
                if (receiver == null) {
                    return;
                }
                boolean copy = transferMode.getSelectionModel().getSelectedItem() == TransferMode.COPY;
                event.getDragboard().getFiles().forEach(file -> {
                    try {
                        // TODO: Async
                        receiver.transfer(file.toPath(), copy);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
            });
        }

        @Override
        protected void updateItem(QuickDragTile item, boolean empty) {
            super.updateItem(item, empty);

            if (empty) {
                setText(null);
                setGraphic(null);
                setBackground(null);
            } else {
                setText(item.getName());
                setGraphic(FontIcon.of(item.getIcon(), 32, item.getIconColor()));
                setBackground(new Background(new BackgroundFill(item.getBackGroundColor(), new CornerRadii(5), null)));
            }
        }
    }
}
