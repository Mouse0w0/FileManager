package com.github.mouse0w0.filemanager.storage.setting;

import com.github.mouse0w0.filemanager.file.FileTransfer;
import com.github.mouse0w0.filemanager.util.ColorPersistent;
import com.github.mouse0w0.filemanager.util.FileTransferPersistent;
import com.github.mouse0w0.filemanager.util.IkonPersistent;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.scene.paint.Color;
import org.kordamp.ikonli.Ikon;

public class StorageSetting {

    public static final Gson GSON = new GsonBuilder()
            .registerTypeAdapter(Color.class, ColorPersistent.INSTANCE)
            .registerTypeAdapter(Ikon.class, IkonPersistent.INSTANCE)
            .registerTypeAdapter(FileTransfer.class, FileTransferPersistent.INSTANCE)
            .create();

    public String name;
    public QuickDrag quickDrag;
}
