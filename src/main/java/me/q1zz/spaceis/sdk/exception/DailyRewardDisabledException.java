package me.q1zz.spaceis.sdk.exception;

import org.jetbrains.annotations.NotNull;

/**
 * Exception thrown when the daily reward feature is disabled.
 */
public class DailyRewardDisabledException extends SpaceIsSdkException {

    /**
     * Constructs a new DailyRewardDisabledException with the specified error message.
     *
     * @param message the detail message of the exception
     */
    public DailyRewardDisabledException(@NotNull String message) {
        super(message);
    }

}
