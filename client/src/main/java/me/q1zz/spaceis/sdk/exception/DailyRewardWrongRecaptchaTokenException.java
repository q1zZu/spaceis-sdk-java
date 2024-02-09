package me.q1zz.spaceis.sdk.exception;

import org.jetbrains.annotations.NotNull;

/**
 * Exception thrown when the provided reCAPTCHA token for the daily reward is invalid.
 */
public class DailyRewardWrongRecaptchaTokenException extends SpaceIsSdkException {

    /**
     * Constructs a new DailyRewardWrongRecaptchaTokenException with the specified error message.
     *
     * @param message the detail message of the exception
     */
    public DailyRewardWrongRecaptchaTokenException(@NotNull String message) {
        super(message);
    }

}
