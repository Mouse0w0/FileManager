package com.github.mouse0w0.filemanager.storage;

import com.github.mouse0w0.filemanager.storage.setting.StorageSetting;

import java.nio.file.Path;

public class Storage {

    private final Path path;

    private StorageSetting setting;

    public Storage(Path path) {
        this.path = path;
    }

    public Path getPath() {
        return path;
    }

    public StorageSetting getSetting() {
        return setting;
    }

    public void loadSetting() {

    }

    public void saveSetting() {

    }
}
