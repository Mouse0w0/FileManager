package com.github.mouse0w0.filemanager.ui.vfs;

import javafx.scene.control.ListCell;

import java.nio.file.Path;

public class FileListCell extends ListCell<Path> {

    private final FileExplorerUI fileExplorer;

    public FileListCell(FileExplorerUI fileExplorer) {
        this.fileExplorer = fileExplorer;
    }

    @Override
    protected void updateItem(Path item, boolean empty) {
        super.updateItem(item, empty);
        if (item == null) {
            return;
        }

        if (item.getNameCount() == 0) {
            setText(item.toString());
        } else {
            setText(item.getFileName().toString());
        }
    }
}
