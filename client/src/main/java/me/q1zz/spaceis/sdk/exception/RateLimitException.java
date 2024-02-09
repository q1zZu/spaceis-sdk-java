package me.q1zz.spaceis.sdk.exception;

import org.jetbrains.annotations.NotNull;

/**
 * Exception thrown when the rate limit is reached.
 */
public class RateLimitException extends SpaceIsSdkException {

    /**
     * Constructs a new RateLimitException with the specified error message.
     *
     * @param message the detail message of the exception
     */
    public RateLimitException(@NotNull String message) {
        super(message);
    }

}
