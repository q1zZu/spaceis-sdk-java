package me.q1zz.spaceis.sdk;

import me.q1zz.spaceis.sdk.modal.*;
import org.jetbrains.annotations.NotNull;

public interface SpaceIsSdk {

    @NotNull
    SpaceIsResponse<License> getLicense();

    @NotNull
    SpaceIsResponse<DiscountCode> getDiscountCode(@NotNull String code);

    @NotNull
    SpaceIsResponse<Subpage> getSubpage(@NotNull String slug);

    @NotNull
    SpaceIsResponse<DailyReward> getDailyReward();

    @NotNull
    SpaceIsResponse<Void> redeemDailyReward(@NotNull String nick, @NotNull String recaptchaToken);

}
