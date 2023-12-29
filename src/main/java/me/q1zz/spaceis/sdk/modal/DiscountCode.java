package me.q1zz.spaceis.sdk.modal;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.Date;

@Data
public class DiscountCode {

    private int id;

    @SerializedName("user_license_id")
    private int userLicenseId;

    private String code;

    private int percentage;

    private int uses;

    @SerializedName("created_at")
    private Date createdAt;

    @SerializedName("updated_at")
    private Date updatedAt;

    @SerializedName("deleted_at")
    private Date deletedAt;

}
