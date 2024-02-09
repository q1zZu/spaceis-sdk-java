package me.q1zz.spaceis.sdk.exception;

import kong.unirest.HttpResponse;
import org.jetbrains.annotations.NotNull;

/**
 * Exception class representing errors encountered while using the SpaceIs SDK, such as communication errors or unexpected responses.
 */
public class SpaceIsSdkException extends RuntimeException {

    /**
     * Constructs a new SpaceIsSdkException with the specified error message.
     *
     * @param message the detail message of the exception
     */
    public SpaceIsSdkException(@NotNull String message) {
        super(message);
    }

    /**
     * Constructs a new SpaceIsSdkException with the error details extracted from the provided HTTP response.
     *
     * @param response the HTTP response from which to extract error details
     */
    public SpaceIsSdkException(HttpResponse<?> response) {
        this(String.format("#%s - %s | %s", response.getStatus(), response.getStatusText(), response.getBody()));
    }

}
