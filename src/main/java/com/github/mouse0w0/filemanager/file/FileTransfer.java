package com.github.mouse0w0.filemanager.file;

import com.google.gson.JsonElement;
import javafx.scene.Parent;

import java.nio.file.Path;

public interface FileTransfer {

    String getName();

    Path getTarget();

    JsonElement getSetting();

    void transfer(Path path, boolean copy);

    interface Factory {
        Parent createSettingUI();

        JsonElement resolveSettingUI(Parent parent);

        FileTransfer create(Path target, JsonElement element);
    }
}
