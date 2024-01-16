package me.q1zz.spaceis.sdk.exception;

import org.jetbrains.annotations.NotNull;

public class ServerCommandNotFoundException extends SpaceIsSdkException {

    public ServerCommandNotFoundException(@NotNull String message) {
        super(message);
    }

}
