package me.q1zz.spaceis.sdk.modal;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class ServerProductVariantPriceSms {

    @SerializedName("is")
    private Boolean enabled;

    private String number;

    private String content;

}
