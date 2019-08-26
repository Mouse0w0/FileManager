package com.github.mouse0w0.filemanager.ui;

import com.github.mouse0w0.filemanager.ui.quickdrag.QuickDragPane;
import javafx.scene.layout.BorderPane;

public class MainUI extends BorderPane {

    public MainUI() {
        UIHelper.load(this, "Main");
        setCenter(new QuickDragPane());
    }
}
