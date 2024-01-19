package me.q1zz.spaceis.sdk.modal;

import lombok.Data;

@Data
public class ServerProductVariantPrices {
    private ServerProductVariantPriceSms sms;
    private int amount;
    private String name;
    private String method;
    private String type;
    private ServerProductVariantMethodProviderData providerData;

}
