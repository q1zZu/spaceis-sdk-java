package me.q1zz.spaceis.sdk.util;

import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.NotNull;

import java.util.Base64;

@UtilityClass
public class Base64Util {

    private final static Base64.Decoder DECODER = Base64.getDecoder();

    @NotNull
    public String decode(@NotNull String base64) {
        return new String(DECODER.decode(base64));
    }


}
