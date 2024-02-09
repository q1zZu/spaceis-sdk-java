package me.q1zz.spaceis.sdk.exception;

import org.jetbrains.annotations.NotNull;

/**
 * Exception thrown when a requested resource is not found.
 */
public class NotFoundException extends SpaceIsSdkException {

    /**
     * Constructs a new NotFoundException with the specified error message.
     *
     * @param message the detail message of the exception
     */
    public NotFoundException(@NotNull String message) {
        super(message);
    }

}
