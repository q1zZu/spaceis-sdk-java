package me.q1zz.spaceis.sdk.exception;

import org.jetbrains.annotations.NotNull;

public class ServerNotFoundException extends SpaceIsSdkException {

    public ServerNotFoundException(@NotNull String message) {
        super(message);
    }

}
