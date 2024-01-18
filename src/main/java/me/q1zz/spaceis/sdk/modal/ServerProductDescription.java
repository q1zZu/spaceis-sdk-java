package me.q1zz.spaceis.sdk.modal;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.q1zz.spaceis.sdk.util.Base64Util;
import org.jetbrains.annotations.NotNull;

@Data
public class ServerProductDescription {

    @SerializedName("short")
    private String shortDescription;

    @SerializedName("long")
    private String longDescription;

    @NotNull
    public String getLongDescription() {

        if(this.longDescription.isEmpty()) {
            return "";
        }

        return Base64Util.decode(this.longDescription);
    }

}
