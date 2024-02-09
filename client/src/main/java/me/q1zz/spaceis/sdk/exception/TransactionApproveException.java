package me.q1zz.spaceis.sdk.exception;

import org.jetbrains.annotations.NotNull;

/**
 * Exception thrown when there is an issue while approving a transaction.
 */
public class TransactionApproveException extends SpaceIsSdkException {

    /**
     * Constructs a new TransactionApproveException with the specified error message.
     *
     * @param message the detail message of the exception
     */
    public TransactionApproveException(@NotNull String message) {
        super(message);
    }

}
