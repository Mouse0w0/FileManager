package com.github.mouse0w0.filemanager.ui.drag;

import com.github.mouse0w0.filemanager.file.FileReceiver;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.util.StringConverter;
import org.controlsfx.control.GridCell;
import org.controlsfx.control.GridView;
import org.kordamp.ikonli.javafx.FontIcon;

public class FileDragPane extends BorderPane {

    private final GridView<DragTile> grid = new GridView<>();
    private final ChoiceBox<TransferMode> transferMode = new ChoiceBox<>();

    public FileDragPane() {
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

        Text transferModeText = new Text("Transfer Mode:");
        hBox.getChildren().addAll(transferModeText, transferMode);
    }

    private class Cell extends GridCell<DragTile> {

        public Cell() {
            setAlignment(Pos.CENTER);
            setContentDisplay(ContentDisplay.TOP);
            setOnDragOver(event -> {
                if (event.getGestureSource() != this) {
                    event.acceptTransferModes(transferMode.getSelectionModel().getSelectedItem());
                }
            });
            setOnDragDropped(event -> {
                FileReceiver receiver = getItem().getReceiver();
                if (receiver == null) {
                    return;
                }
                event.getDragboard().getFiles().forEach(file -> receiver.onReceive(file.toPath()));
            });
        }

        @Override
        protected void updateItem(DragTile item, boolean empty) {
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
