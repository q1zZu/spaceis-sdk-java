package me.q1zz.spaceis.sdk.exception;

import org.jetbrains.annotations.NotNull;

public class NotFoundException extends SpaceIsSdkException {

    public NotFoundException(@NotNull String message) {
        super(message);
    }

}
