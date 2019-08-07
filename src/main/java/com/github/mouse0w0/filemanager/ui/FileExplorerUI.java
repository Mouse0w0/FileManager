package com.github.mouse0w0.filemanager.ui;

import com.github.mouse0w0.filemanager.ui.vfs.FileTreeCell;
import com.github.mouse0w0.filemanager.ui.vfs.FileTreeItem;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.nio.file.FileSystems;
import java.nio.file.Path;

public class FileExplorerUI extends SplitPane {

    @FXML
    private TreeView<Path> tree;

    private TreeItem<Path> root;

    @FXML
    private TextField currentPath;

    @FXML
    private ListView<Path> list;

    public FileExplorerUI() {
        UIHelper.load(this, "FileExplorer");
        tree.setCellFactory(param -> new FileTreeCell());
        tree.setShowRoot(false);
        root = new TreeItem<>();
        tree.setRoot(root);
        FileSystems.getDefault().getRootDirectories().forEach(path -> root.getChildren().add(new FileTreeItem(path)));
    }
}