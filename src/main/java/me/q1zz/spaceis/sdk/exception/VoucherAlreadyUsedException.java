package me.q1zz.spaceis.sdk.exception;

import org.jetbrains.annotations.NotNull;

public class VoucherAlreadyUsedException extends SpaceIsSdkException {

    public VoucherAlreadyUsedException(@NotNull String message) {
        super(message);
    }

}
