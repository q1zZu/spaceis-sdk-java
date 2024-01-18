package me.q1zz.spaceis.sdk.modal;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.UUID;

@Data
public class ServerProduct {

    private UUID id;
    private String name;
    private String imageUrl;

    @SerializedName("parameter_1")
    private String firstParameter;

    @SerializedName("parameter_2")
    private String secondParameter;

    @SerializedName("descriptions")
    private ServerProductDescription description;

    @SerializedName("is_promoted")
    private Boolean isPromoted;

    @SerializedName("promotion_percentage")
    private int promotionPercentage;

    private String paymentUrl;

}
