package com.github.mouse0w0.filemanager.transfer;

import com.github.mouse0w0.filemanager.storage.Storage;
import com.github.mouse0w0.filemanager.ui.transfer.FileTransferNormalUI;
import com.google.gson.JsonElement;
import javafx.scene.Parent;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static com.github.mouse0w0.filemanager.util.PathUtils.relativize;
import static com.github.mouse0w0.filemanager.util.json.JsonUtils.json;

public class FileTransferNormal extends FileTransferBase {

    private final String target;

    public FileTransferNormal(Factory factory, String target) {
        super(factory);
        this.target = target;
    }

    @Override
    public JsonElement writeSettings() {
        return json(target);
    }

    @Override
    public void transfer(Storage storage, Path path, boolean copy) throws IOException {
        Path target = storage.getPath().resolve(this.target).resolve(path.getFileName());
        if (copy) {
            Files.copy(path, target);
        } else {
            Files.move(path, target);
        }
    }

    public enum Factory implements FileTransfer.Factory {

        INSTANCE;

        @Override
        public String getName() {
            return "Normal";
        }

        @Override
        public Parent createSettingUI(Storage storage, JsonElement setting) {
            return new FileTransferNormalUI(storage);
        }

        @Override
        public JsonElement resolveSettingUI(Storage storage, Parent parent) {
            return json(relativize(storage.getPath(), ((FileTransferNormalUI) parent).path.getText()).toString());
        }

        @Override
        public FileTransfer create(JsonElement setting) {
            return new FileTransferNormal(this, setting.getAsString());
        }
    }
}
