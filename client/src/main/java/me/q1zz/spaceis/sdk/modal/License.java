package me.q1zz.spaceis.sdk.modal;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.Date;

/**
 * Represents license information.
 */
@Data
public class License {

    /**
     * The ID of the license.
     */
    private int id;

    /**
     * The ID of the user associated with the license.
     */
    @SerializedName("user_id")
    private int userId;

    /**
     * The ID of the license plan associated with the license.
     */
    @SerializedName("license_plan_id")
    private int licensePlanId;

    /**
     * Indicates whether the license is active or not.
     */
    @SerializedName("is_active")
    private Boolean active;

    /**
     * Indicates whether payments are disabled.
     */
    @SerializedName("disable_payments")
    private Boolean disablePayments;

    /**
     * The second ID associated with the license.
     */
    @SerializedName("second_id")
    private String secondId;

    /**
     * The domain associated with the license.
     */
    private String domain;

    /**
     * The date and time when the license expires.
     */
    @SerializedName("expire_at")
    private Date expireAt;

    /**
     * The date and time when the license was last updated.
     */
    @SerializedName("updated_at")
    private Date updatedAt;

    /**
     * The date and time when the license was created.
     */
    @SerializedName("created_at")
    private Date createdAt;

}
