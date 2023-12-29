package me.q1zz.spaceis.sdk.exception;

import kong.unirest.HttpResponse;
import org.jetbrains.annotations.NotNull;

public class SpaceIsSdkException extends RuntimeException {

    public SpaceIsSdkException(@NotNull String message) {
        super(message);
    }

    public SpaceIsSdkException(HttpResponse<?> response) {
        this(String.format("#%s - %s | %s", response.getStatus(), response.getStatusText(), response.getBody()));
    }

}
