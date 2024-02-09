package me.q1zz.spaceis.sdk.modal;

import lombok.Data;

import java.util.UUID;

/**
 * Represents a variant of a server product.
 */
@Data
public class ServerProductVariant {

    /**
     * The ID of the variant.
     */
    private UUID id;

    /**
     * The name of the variant.
     */
    private String name;

    /**
     * The prices associated with the variant.
     */
    private ServerProductVariantPrices[] prices;

}
