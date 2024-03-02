package me.q1zz.spaceis.sdk.modal.server;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.q1zz.spaceis.sdk.util.Base64Util;
import org.jetbrains.annotations.NotNull;

import java.util.Date;
import java.util.UUID;

/**
 * Represents information about a server product.
 */
@Data
public class ServerProductInfo {

    /**
     * The ID of the product.
     */
    private UUID id;

    /**
     * The ID of the user license associated with the product.
     */
    @SerializedName("user_license_id")
    private int userLicenseId;

    /**
     * The ID of the category to which the product belongs.
     */
    @SerializedName("category_id")
    private UUID categoryId;

    /**
     * The name of the product.
     */
    private String name;

    /**
     * A short description of the product.
     */
    @SerializedName("short_description")
    private String shortDescription;

    /**
     * A long description of the product.
     */
    @SerializedName("long_description")
    private String longDescription;

    /**
     * The URL of the product's image.
     */
    @SerializedName("image_url")
    private String imageUrl;

    /**
     * The position of the product.
     */
    private int position;

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
     * The date and time when the product was created.
     */
    @SerializedName("created_at")
    private Date createdAt;

    /**
     * The date and time when the product was last updated.
     */
    @SerializedName("updated_at")
    private Date updatedAt;

    /**
     * The date and time when the product was deleted.
     */
    @SerializedName("deleted_at")
    private Date deletedAt;

    /**
     * Indicates if the product is promoted.
     */
    @SerializedName("is_promoted")
    private Boolean isPromoted;

    /**
     * The percentage of promotion for the product.
     */
    @SerializedName("promotion_percentage")
    private int promotionPercentage;

    /**
     * Retrieves the decoded long description of the product.
     *
     * @return The decoded long description.
     */
    @NotNull
    public String getLongDescription() {

        if(this.longDescription.isBlank()) {
            return "";
        }

        return Base64Util.decode(this.longDescription);
    }

}
