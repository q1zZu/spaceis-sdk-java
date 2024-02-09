package me.q1zz.spaceis.sdk.util;

import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.NotNull;

import java.util.Base64;

/**
 * Utility class providing methods for encoding and decoding Base64 strings.
 */
@UtilityClass
public class Base64Util {

    /**
     * The Base64 decoder instance used for decoding Base64 strings.
     */
    private final static Base64.Decoder DECODER = Base64.getDecoder();

    /**
     * Decodes the provided Base64 string.
     *
     * @param base64 the Base64 string to decode
     * @return the decoded string
     * @throws IllegalArgumentException if the input string is not a valid Base64 encoded string
     */
    @NotNull
    public String decode(@NotNull String base64) {
        return new String(DECODER.decode(base64));
    }

}
