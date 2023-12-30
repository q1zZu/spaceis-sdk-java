package me.q1zz.spaceis.sdk.request;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class VoucherGenerateRequest {

    private int quantity;
    private UUID variantId;

}
