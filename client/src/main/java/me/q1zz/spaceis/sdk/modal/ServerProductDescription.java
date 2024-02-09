package me.q1zz.spaceis.sdk.modal;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.q1zz.spaceis.sdk.util.Base64Util;
import org.jetbrains.annotations.NotNull;

/**
 * Represents the description of a server product.
 */
@Data
public class ServerProductDescription {

    /**
     * The short description of the product.
     */
    @SerializedName("short")
    private String shortDescription;

    /**
     * The long description of the product.
     */
    @SerializedName("long")
    private String longDescription;

    /**
     * Retrieves the decoded long description of the product.
     *
     * @return the decoded long description
     */
    @NotNull
    public String getLongDescription() {

        if(this.longDescription.isEmpty()) {
            return "";
        }

        return Base64Util.decode(this.longDescription);
    }

}
