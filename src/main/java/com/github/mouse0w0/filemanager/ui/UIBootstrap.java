package com.github.mouse0w0.filemanager.ui;

import com.github.mouse0w0.filemanager.FileManager;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class UIBootstrap extends Application {

    private static Stage mainStage;

    public static Stage getMainStage() {
        return mainStage;
    }

    @Override
    public void start(Stage primaryStage) {
        mainStage = primaryStage;
        primaryStage.setScene(new Scene(new MainUI(primaryStage)));
        primaryStage.setTitle("File Manager");
        primaryStage.show();
        primaryStage.setOnCloseRequest(event -> FileManager.shutdown());
    }
}
