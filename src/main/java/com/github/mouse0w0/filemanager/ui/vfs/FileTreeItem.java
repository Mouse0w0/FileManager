package com.github.mouse0w0.filemanager.ui.vfs;

import javafx.scene.control.TreeItem;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
            getChildren().addAll(Files.list(getValue())
                    .parallel()
                    .flatMap(path -> {
                        try {
                            if (!Files.isHidden(path) && Files.isReadable(path) && Files.isWritable(path)) {
                                return Stream.of(new FileTreeItem(path));
                            }
                        } catch (IOException ignored) {
                        }
                        return Stream.empty();
                    })
                    .collect(Collectors.toList()));
        } catch (IOException ignored) {
        }
    }
}