package me.q1zz.spaceis.sdk.modal;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.q1zz.spaceis.sdk.util.Base64Util;
import org.jetbrains.annotations.NotNull;

import java.util.Date;
import java.util.UUID;

@Data
public class ServerProductInfo {

    private UUID id;

    @SerializedName("user_license_id")
    private int userLicenseId;

    @SerializedName("category_id")
    private UUID categoryId;

    private String name;

    @SerializedName("short_description")
    private String shortDescription;

    @SerializedName("long_description")
    private String longDescription;

    @SerializedName("image_url")
    private String imageUrl;

    private int position;

    @SerializedName("parameter_1")
    private String firstParameter;

    @SerializedName("parameter_2")
    private String secondParameter;

    @SerializedName("created_at")
    private Date createdAt;

    @SerializedName("updated_at")
    private Date updatedAt;

    @SerializedName("deleted_at")
    private Date deletedAt;

    @SerializedName("is_promoted")
    private Boolean isPromoted;

    @SerializedName("promotion_percentage")
    private int promotionPercentage;

    @NotNull
    public String getLongDescription() {

        if(this.longDescription.isBlank()) {
            return "";
        }

        return Base64Util.decode(this.longDescription);
    }

}
