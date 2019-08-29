package com.github.mouse0w0.filemanager.ui.quickdrag;

import com.github.mouse0w0.filemanager.file.FileTransfer;
import com.github.mouse0w0.filemanager.storage.setting.QuickDragTile;
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

public class QuickDragPane extends BorderPane {

    private final GridView<QuickDragTile> grid = new GridView<>();
    private final ChoiceBox<TransferMode> transferMode = new ChoiceBox<>();

    public QuickDragPane() {
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
        setCenter(grid);
    }

    private void initSetting() {
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER_RIGHT);
        hBox.setSpacing(5);
        setBottom(hBox);

        Button addTile = new Button("Add Tile");
        addTile.setOnAction(event -> NewQuickDragTileUI.show(getScene().getWindow()));

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
                event.getDragboard().getFiles().forEach(file -> receiver.transfer(file.toPath(), copy));
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
