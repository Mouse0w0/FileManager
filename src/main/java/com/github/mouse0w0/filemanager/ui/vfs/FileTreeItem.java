package com.github.mouse0w0.filemanager.ui.vfs;

import javafx.scene.control.TreeItem;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;

public class FileTreeItem extends TreeItem<Path> {

    public FileTreeItem(Path value) {
        super(value);

        if (!Files.isDirectory(getValue())) {
            return;
        }

        expandedProperty().addListener((observable, oldValue, newValue) -> {
            getChildren().clear();
            try {
                if (newValue) {
                    initFileTreeItems();
                } else {
                    if (Files.list(getValue()).count() > 0) {
                        getChildren().add(new TreeItem<>());
                    }
                }
            } catch (IOException ignored) {
            }
        });

        try {
            if (Files.list(getValue()).count() > 0) {
                getChildren().add(new TreeItem<>());
            }
        } catch (IOException ignored) {
        }
    }

    private void initFileTreeItems() {
        try {
            for (var path : Files.list(getValue()).collect(Collectors.toList())) {
                if (Files.isHidden(path) || !Files.isReadable(path) || !Files.isWritable(path)) {
                    continue;
                }

                getChildren().add(new FileTreeItem(path));
            }
        } catch (IOException ignored) {
        }
    }
}