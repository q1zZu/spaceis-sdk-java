package me.q1zz.spaceis.sdk.exception;

import org.jetbrains.annotations.NotNull;

/**
 * Exception thrown when the daily reward has already been received by the player.
 */
public class DailyRewardAlreadyReceivedException extends SpaceIsSdkException {

    /**
     * Constructs a new DailyRewardAlreadyReceivedException with the specified error message.
     *
     * @param message the detail message of the exception
     */
    public DailyRewardAlreadyReceivedException(@NotNull String message) {
        super(message);
    }

}
