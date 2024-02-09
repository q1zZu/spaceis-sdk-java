package me.q1zz.spaceis.sdk.exception;

import org.jetbrains.annotations.NotNull;

/**
 * Exception thrown when a voucher is not found.
 */
public class VoucherNotFoundException extends SpaceIsSdkException {

    /**
     * Constructs a new VoucherNotFoundException with the specified error message.
     *
     * @param message the detail message of the exception
     */
    public VoucherNotFoundException(@NotNull String message) {
        super(message);
    }

}
