package me.q1zz.spaceis.sdk.modal;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.Date;

/**
 * Represents discount code information.
 */
@Data
public class DiscountCode {

    /**
     * The ID of the discount code.
     */
    private int id;

    /**
     * The ID of the user license associated with the discount code.
     */
    @SerializedName("user_license_id")
    private int userLicenseId;

    /**
     * The discount code.
     */
    private String code;

    /**
     * The percentage discount offered by the code.
     */
    private int percentage;

    /**
     * The number of times the discount code has been used.
     */
    private int uses;

    /**
     * The date and time when the discount code was created.
     */
    @SerializedName("created_at")
    private Date createdAt;

    /**
     * The date and time when the discount code was last updated.
     */
    @SerializedName("updated_at")
    private Date updatedAt;

    /**
     * The date and time when the discount code was deleted.
     */
    @SerializedName("deleted_at")
    private Date deletedAt;

}
