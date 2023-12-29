package me.q1zz.spaceis.sdk;

import me.q1zz.spaceis.sdk.modal.DiscountCode;
import me.q1zz.spaceis.sdk.modal.License;
import me.q1zz.spaceis.sdk.modal.SpaceIsResponse;
import me.q1zz.spaceis.sdk.modal.Subpage;
import org.jetbrains.annotations.NotNull;

public interface SpaceIsSdk {

    @NotNull
    SpaceIsResponse<License> getLicense();

    @NotNull
    SpaceIsResponse<DiscountCode> getDiscountCode(@NotNull String code);

    @NotNull
    SpaceIsResponse<Subpage> getSubpage(@NotNull String slug);

}
