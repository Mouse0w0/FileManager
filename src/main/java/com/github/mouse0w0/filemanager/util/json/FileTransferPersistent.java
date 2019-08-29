package com.github.mouse0w0.filemanager.util.json;

import com.github.mouse0w0.filemanager.transfer.FileTransfer;
import com.github.mouse0w0.filemanager.transfer.FileTransfers;
import com.google.gson.*;

import java.lang.reflect.Type;

public enum FileTransferPersistent implements JsonSerializer<FileTransfer>, JsonDeserializer<FileTransfer> {

    INSTANCE;

    @Override
    public FileTransfer deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject object = json.getAsJsonObject();
        FileTransfer.Factory factory = FileTransfers.getFactory(object.get("factory").getAsString());
        return factory.create(object.get("settings"));
    }

    @Override
    public JsonElement serialize(FileTransfer src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject object = new JsonObject();
        object.addProperty("factory", src.getFactory().getName());
        object.add("settings", src.writeSettings());
        return object;
    }
}
