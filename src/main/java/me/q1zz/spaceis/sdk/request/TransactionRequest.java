package me.q1zz.spaceis.sdk.request;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class TransactionRequest {

    private UUID variantId;
    private String nick;
    private String method;
    private String methodParameter;
    private String additional;
    private String clientIp;
    private String discountCodeId;

}
