package me.q1zz.spaceis.sdk.modal;

import lombok.Data;

/**
 * Represents the prices for a server product variant.
 */
@Data
public class ServerProductVariantPrices {

    /**
     * The SMS pricing details for the variant.
     */
    private ServerProductVariantPriceSms sms;

    /**
     * The price of the variant.
     */
    private int amount;

    /**
     * The name of the variant.
     */
    private String name;

    /**
     * The method associated with the variant.
     */
    private String method;

    /**
     * The type of the variant.
     */
    private String type;

    /**
     * The payment provider data associated with the variant.
     */
    private ServerProductVariantMethodProviderData providerData;

}
