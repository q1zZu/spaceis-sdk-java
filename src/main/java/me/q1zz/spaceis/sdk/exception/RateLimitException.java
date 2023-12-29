package me.q1zz.spaceis.sdk.exception;

import org.jetbrains.annotations.NotNull;

public class RateLimitException extends SpaceIsSdkException {

    public RateLimitException(@NotNull String message) {
        super(message);
    }

}
