package com.github.mouse0w0.filemanager.util;

import com.google.gson.*;
import org.kordamp.ikonli.Ikon;
import org.kordamp.ikonli.javafx.IkonResolver;

import java.lang.reflect.Type;

public enum IkonPersistent implements JsonSerializer<Ikon>, JsonDeserializer<Ikon> {

    INSTANCE;

    @Override
    public JsonElement serialize(Ikon src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(src.getDescription());
    }

    @Override
    public Ikon deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return IkonResolver.getInstance().resolveIkonHandler(json.getAsString()).resolve(json.getAsString());
    }
}
