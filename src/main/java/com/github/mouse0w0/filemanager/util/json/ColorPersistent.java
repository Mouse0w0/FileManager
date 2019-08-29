package com.github.mouse0w0.filemanager.util.json;

import com.google.gson.*;
import javafx.scene.paint.Color;

import java.lang.reflect.Type;

public enum ColorPersistent implements JsonSerializer<Color>, JsonDeserializer<Color> {

    INSTANCE;

    @Override
    public JsonElement serialize(Color src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(src.toString());
    }

    @Override
    public Color deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return Color.valueOf(json.getAsString());
    }
}
