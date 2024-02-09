package me.q1zz.spaceis.sdk.exception;

import org.jetbrains.annotations.NotNull;

/**
 * Exception thrown when attempting to redeem a voucher that has already been used.
 */
public class VoucherAlreadyUsedException extends SpaceIsSdkException {

    /**
     * Constructs a new VoucherAlreadyUsedException with the specified error message.
     *
     * @param message the detail message of the exception
     */
    public VoucherAlreadyUsedException(@NotNull String message) {
        super(message);
    }

}
