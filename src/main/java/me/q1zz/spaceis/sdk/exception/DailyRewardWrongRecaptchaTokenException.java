package me.q1zz.spaceis.sdk.exception;

import org.jetbrains.annotations.NotNull;

public class DailyRewardWrongRecaptchaTokenException extends SpaceIsSdkException {

    public DailyRewardWrongRecaptchaTokenException(@NotNull String message) {
        super(message);
    }

}
