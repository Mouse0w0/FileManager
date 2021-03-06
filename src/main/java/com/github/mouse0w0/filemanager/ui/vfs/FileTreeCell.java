package com.github.mouse0w0.filemanager.ui.vfs;

import javafx.scene.control.TreeCell;

import java.nio.file.Path;

public class FileTreeCell extends TreeCell<Path> {

    private final FileExplorerUI fileExplorer;

    public FileTreeCell(FileExplorerUI fileExplorer) {
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
