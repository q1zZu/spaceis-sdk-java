package me.q1zz.spaceis.sdk.adapter;

import com.google.gson.*;

import java.lang.reflect.Type;

public class BooleanAdapter implements JsonSerializer<Boolean>, JsonDeserializer<Boolean> {

    @Override
    public JsonElement serialize(Boolean src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(src ? 1 : 0);
    }

    @Override
    public Boolean deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return json.getAsInt() != 0;
    }

}
