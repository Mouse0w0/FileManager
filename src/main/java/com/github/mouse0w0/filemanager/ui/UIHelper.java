package com.github.mouse0w0.filemanager.ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class UIHelper {

    public static void load(Object root, String name) {
        FXMLLoader loader = new FXMLLoader();
        loader.setController(root);
        loader.setRoot(root);
        loader.setCharset(StandardCharsets.UTF_8);
        try {
            loader.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("ui/" + name + ".fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void load(Object root, URL url) {
        FXMLLoader loader = new FXMLLoader(url);
        loader.setController(root);
        loader.setRoot(root);
        loader.setCharset(StandardCharsets.UTF_8);
        try {
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void showUtilityWindow(Node parent, String title, Parent graphics) {
        showUtilityWindow(parent.getScene().getWindow(), title, graphics);
    }


    public static void showUtilityWindow(Window parent, String title, Parent graphics) {
        showUtilityWindow(parent, title, new Scene(graphics));
    }

    public static void showUtilityWindow(Window parent, String title, Scene scene) {
        Stage stage = new Stage();
        stage.initOwner(parent);
        stage.initStyle(StageStyle.UTILITY);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle(title);
        stage.setScene(scene);
        stage.showAndWait();
    }

    public static void closeWindow(Node node) {
        Window window = node.getScene().getWindow();
        if (window instanceof Stage) {
            ((Stage) window).close();
        }
    }
}
