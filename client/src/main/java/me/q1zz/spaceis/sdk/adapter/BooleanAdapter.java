package me.q1zz.spaceis.sdk.adapter;

import com.google.gson.*;

import java.lang.reflect.Type;

/**
 * Adapter for serializing and deserializing boolean values in JSON format (e.g. 0 [false] or 1 [true]).
 */
public class BooleanAdapter implements JsonSerializer<Boolean>, JsonDeserializer<Boolean> {

    /**
     * Serializes a boolean value to JSON format.
     *
     * @param src         the boolean value to serialize
     * @param typeOfSrc   the type of the source object
     * @param context     the serialization context
     * @return            the serialized JSON element representing the boolean value
     */
    @Override
    public JsonElement serialize(Boolean src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(src ? 1 : 0);
    }

    /**
     * Deserializes a boolean value from JSON format.
     *
     * @param json        the JSON element to deserialize
     * @param typeOfT     the type of the target object
     * @param context     the deserialization context
     * @return            the deserialized boolean value
     * @throws JsonParseException if an error occurs during deserialization
     */
    @Override
    public Boolean deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return json.getAsInt() != 0;
    }

}
