package com.github.mouse0w0.filemanager;

import com.github.mouse0w0.filemanager.ui.UIBootstrap;
import javafx.application.Application;

public class FileManager {

    public static void main(String[] args) {
        UserSettings.init();
        Application.launch(UIBootstrap.class, args);
    }

    public static void shutdown() {

    }
}
