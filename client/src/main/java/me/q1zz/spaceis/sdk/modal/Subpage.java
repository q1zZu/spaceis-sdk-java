package me.q1zz.spaceis.sdk.modal;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.Date;

/**
 * Represents a subpage.
 */
@Data
public class Subpage {

    /**
     * The ID of the subpage.
     */
    private int id;

    /**
     * The ID of the user license associated with the subpage.
     */
    @SerializedName("user_license_id")
    private int userLicenseId;

    /**
     * The name of the subpage.
     */
    private String name;

    /**
     * The slug of the subpage.
     */
    private String slug;

    /**
     * The content of the subpage (base64).
     */
    private String content;

    /**
     * The date and time when the subpage was last updated.
     */
    @SerializedName("updated_at")
    private Date updatedAt;

    /**
     * The date and time when the subpage was created.
     */
    @SerializedName("created_at")
    private Date createdAt;

}
