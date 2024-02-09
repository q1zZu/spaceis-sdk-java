package me.q1zz.spaceis.sdk.modal;

import lombok.Data;

/**
 * Represents the payment provider data for a server product variant.
 */
@Data
public class ServerProductVariantMethodProviderData {

    /**
     * The terms URL associated with the payment provider.
     */
    private String terms;

    /**
     * The complaint URL associated with the payment provider.
     */
    private String complaint;

    /**
     * The home page URL associated with the payment provider.
     */
    private String home;

    /**
     * The image URL associated with the payment provider.
     */
    private String image;

    /**
     * The name associated with the payment provider.
     */
    private String name;

}
