package me.q1zz.spaceis.sdk.modal;

import lombok.Data;

/**
 * Represents daily reward information.
 */
@Data
public class DailyReward {

    /**
     * The name of the daily reward.
     */
    private String name;

    /**
     * The reCAPTCHA site key required for daily reward verification.
     */
    private String recaptchaSiteKey;

    /**
     * The day of the week when the daily reward is available.
     */
    private int dayOfWeek;

}
