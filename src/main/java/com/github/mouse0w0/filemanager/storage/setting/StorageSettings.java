package com.github.mouse0w0.filemanager.storage.setting;

import com.github.mouse0w0.filemanager.transfer.FileTransfer;
import com.github.mouse0w0.filemanager.util.json.ColorPersistent;
import com.github.mouse0w0.filemanager.util.json.FileTransferPersistent;
import com.github.mouse0w0.filemanager.util.json.IkonPersistent;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.scene.paint.Color;
import org.kordamp.ikonli.Ikon;

import java.util.ArrayList;
import java.util.List;

public class StorageSettings {

    public static final Gson GSON = new GsonBuilder()
            .registerTypeAdapter(Color.class, ColorPersistent.INSTANCE)
            .registerTypeAdapter(Ikon.class, IkonPersistent.INSTANCE)
            .registerTypeAdapter(FileTransfer.class, FileTransferPersistent.INSTANCE)
            .create();

    private String name;
    private List<QuickDrag> quickDrags = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<QuickDrag> getQuickDrags() {
        return quickDrags;
    }
}
