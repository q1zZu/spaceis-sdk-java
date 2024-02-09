package me.q1zz.spaceis.sdk.exception;

import org.jetbrains.annotations.NotNull;

/**
 * Exception thrown when a server command is not found.
 */
public class ServerCommandNotFoundException extends SpaceIsSdkException {

    /**
     * Constructs a new ServerCommandNotFoundException with the specified error message.
     *
     * @param message the detail message of the exception
     */
    public ServerCommandNotFoundException(@NotNull String message) {
        super(message);
    }

}
