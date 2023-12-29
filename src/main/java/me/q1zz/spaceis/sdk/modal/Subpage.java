package me.q1zz.spaceis.sdk.modal;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.Date;

@Data
public class Subpage {

    private int id;

    @SerializedName("user_license_id")
    private int userLicenseId;

    private String name;

    private String slug;

    private String content;

    @SerializedName("updated_at")
    private Date updatedAt;

    @SerializedName("created_at")
    private Date createdAt;

}
