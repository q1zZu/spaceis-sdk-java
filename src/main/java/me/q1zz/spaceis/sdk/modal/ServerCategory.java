package me.q1zz.spaceis.sdk.modal;

import lombok.Data;

import java.util.UUID;

/**
 * Represents a category associated with a server, containing a list of products.
 */
@Data
public class ServerCategory {

    /**
     * The ID of the category.
     */
    private UUID id;

    /**
     * The name of the category.
     */
    private String name;

    /**
     * The array of products associated with the category.
     */
    private ServerProduct[] products;

}
