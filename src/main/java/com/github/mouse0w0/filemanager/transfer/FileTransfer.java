package com.github.mouse0w0.filemanager.transfer;

import com.github.mouse0w0.filemanager.storage.Storage;
import com.google.gson.JsonElement;
import javafx.scene.Parent;

import java.io.IOException;
import java.nio.file.Path;

public interface FileTransfer {

    Factory getFactory();

    JsonElement writeSettings();

    void transfer(Storage storage, Path path, boolean copy) throws IOException;

    interface Factory {

        String getName();

        FileTransfer create(JsonElement setting);

        Parent createSettingUI(Storage storage, JsonElement setting);

        JsonElement resolveSettingUI(Storage storage, Parent parent);
    }
}
