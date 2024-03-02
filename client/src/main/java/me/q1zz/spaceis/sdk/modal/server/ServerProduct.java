package me.q1zz.spaceis.sdk.modal.server;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.UUID;

/**
 * Represents a product associated with a server.
 */
@Data
public class ServerProduct {

    /**
     * The ID of the product.
     */
    private UUID id;

    /**
     * The name of the product.
     */
    private String name;

    /**
     * The URL to the image associated with the product.
     */
    private String imageUrl;

    /**
     * The first parameter of the product.
     */
    @SerializedName("parameter_1")
    private String firstParameter;

    /**
     * The second parameter of the product.
     */
    @SerializedName("parameter_2")
    private String secondParameter;

    /**
     * The descriptions of the product.
     */
    @SerializedName("descriptions")
    private ServerProductDescription description;

    /**
     * Indicates whether the product is promoted.
     */
    @SerializedName("is_promoted")
    private Boolean isPromoted;

    /**
     * The percentage of promotion for the product.
     */
    @SerializedName("promotion_percentage")
    private int promotionPercentage;

    /**
     * The payment URL for purchasing the product.
     */
    private String paymentUrl;

}
