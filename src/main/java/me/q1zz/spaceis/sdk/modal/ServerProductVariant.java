package me.q1zz.spaceis.sdk.modal;

import lombok.Data;

import java.util.UUID;

@Data
public class ServerProductVariant {

    private UUID id;
    private String name;
    private ServerProductVariantPrices[] prices;

}
