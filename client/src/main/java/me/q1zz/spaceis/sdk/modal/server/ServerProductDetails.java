package me.q1zz.spaceis.sdk.modal.server;

import lombok.Data;

/**
 * Represents the details of a server product, including its information and variants.
 */
@Data
public class ServerProductDetails {

    /**
     * The information about the server product.
     */
    private ServerProductInfo product;

    /**
     * The variants of the server product.
     */
    private ServerProductVariant[] variants;

}
