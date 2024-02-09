package me.q1zz.spaceis.sdk.exception;

import org.jetbrains.annotations.NotNull;

/**
 * Exception thrown when a server is not found.
 */
public class ServerNotFoundException extends SpaceIsSdkException {

    /**
     * Constructs a new ServerNotFoundException with the specified error message.
     *
     * @param message the detail message of the exception
     */
    public ServerNotFoundException(@NotNull String message) {
        super(message);
    }

}
