package me.q1zz.spaceis.sdk.exception;

import org.jetbrains.annotations.NotNull;

/**
 * Exception thrown when there is an issue during the initialization of a transaction.
 */
public class TransactionInitException extends SpaceIsSdkException {

    /**
     * Constructs a new TransactionInitException with the specified error message.
     *
     * @param message the detail message of the exception
     */
    public TransactionInitException(@NotNull String message) {
        super(message);
    }

}
