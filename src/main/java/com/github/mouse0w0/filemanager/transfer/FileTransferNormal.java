package com.github.mouse0w0.filemanager.transfer;

import com.github.mouse0w0.filemanager.storage.Storage;
import com.github.mouse0w0.filemanager.ui.transfer.FileTransferNormalUI;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import javafx.scene.Parent;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileTransferNormal extends FileTransferBase {

    private final Path target;

    public FileTransferNormal(Factory factory, Path target) {
        super(factory);
        this.target = target;
    }

    @Override
    public JsonElement writeSetting(Storage storage) {
        return new JsonPrimitive(target.relativize(storage.getPath()).toString());
    }

    @Override
    public void transfer(Path path, boolean copy) throws IOException {
        Path target = this.target.resolve(path.getFileName());
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
            return new JsonPrimitive(Path.of(((FileTransferNormalUI) parent).path.getText()).relativize(storage.getPath()).toString());
        }

        @Override
        public FileTransfer create(Storage storage, JsonElement setting) {
            return new FileTransferNormal(this, storage.getPath().resolve(setting.getAsString()));
        }
    }
}
