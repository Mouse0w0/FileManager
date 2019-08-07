package com.github.mouse0w0.filemanager.ui;

import javafx.scene.layout.BorderPane;

public class MainUI extends BorderPane {

    public MainUI() {
        UIHelper.load(this, "Main");
        setCenter(new FileExplorerUI());
    }
}
