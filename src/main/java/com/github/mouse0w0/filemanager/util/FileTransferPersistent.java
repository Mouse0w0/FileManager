package com.github.mouse0w0.filemanager.util;

import com.github.mouse0w0.filemanager.transfer.FileTransfer;
import com.google.gson.*;

import java.lang.reflect.Type;

public enum FileTransferPersistent implements JsonSerializer<FileTransfer>, JsonDeserializer<FileTransfer> {

    INSTANCE;

    @Override
    public FileTransfer deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return null;
    }

    @Override
    public JsonElement serialize(FileTransfer src, Type typeOfSrc, JsonSerializationContext context) {
        return null;
    }
}
