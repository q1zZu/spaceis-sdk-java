package me.q1zz.spaceis.sdk.modal.server;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * Represents the SMS details for a server product variant.
 */
@Data
public class ServerProductVariantPriceSms {

    /**
     * Indicates whether SMS is enabled for the variant.
     */
    @SerializedName("is")
    private Boolean enabled;

    /**
     * The phone number associated with the SMS.
     */
    private String number;

    /**
     * The content of the SMS.
     */
    private String content;

}
