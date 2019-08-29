package com.github.mouse0w0.filemanager.util.json;

import com.google.gson.JsonPrimitive;

public class JsonUtils {

    public static JsonPrimitive json(String value) {
        return new JsonPrimitive(value);
    }

    public static JsonPrimitive json(Number value) {
        return new JsonPrimitive(value);
    }

    public static JsonPrimitive json(Boolean value) {
        return new JsonPrimitive(value);
    }
}
