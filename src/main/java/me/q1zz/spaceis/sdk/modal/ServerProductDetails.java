package me.q1zz.spaceis.sdk.modal;

import lombok.Data;

@Data
public class ServerProductDetails {
    private ServerProductInfo product;
    private ServerProductVariant[] variants;

}
