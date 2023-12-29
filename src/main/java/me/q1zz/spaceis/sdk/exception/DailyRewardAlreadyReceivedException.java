package me.q1zz.spaceis.sdk.exception;

import org.jetbrains.annotations.NotNull;

public class DailyRewardAlreadyReceivedException extends SpaceIsSdkException {

    public DailyRewardAlreadyReceivedException(@NotNull String message) {
        super(message);
    }

}
