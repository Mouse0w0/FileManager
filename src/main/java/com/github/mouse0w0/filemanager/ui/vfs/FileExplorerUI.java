package com.github.mouse0w0.filemanager.ui.vfs;

import com.github.mouse0w0.filemanager.ui.UIHelper;
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

        tree.setCellFactory(param -> new FileTreeCell(this));
        tree.setShowRoot(false);
        root = new TreeItem<>();
        tree.setRoot(root);
        FileSystems.getDefault().getRootDirectories().forEach(path -> root.getChildren().add(new FileTreeItem(path)));

        list.setCellFactory(param -> new FileListCell(this));
    }

    public void showPathInList(Path path) {

    }
}