package me.q1zz.spaceis.sdk.modal;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.Date;

@Data
public class License {

    private int id;

    @SerializedName("user_id")
    private int userId;

    @SerializedName("license_plan_id")
    private int licensePlanId;

    @SerializedName("is_active")
    private Boolean active;

    @SerializedName("disable_payments")
    private Boolean disablePayments;

    @SerializedName("second_id")
    private String secondId;

    private String domain;

    @SerializedName("expire_at")
    private Date expireAt;

    @SerializedName("updated_at")
    private Date updatedAt;

    @SerializedName("created_at")
    private Date createdAt;

}
