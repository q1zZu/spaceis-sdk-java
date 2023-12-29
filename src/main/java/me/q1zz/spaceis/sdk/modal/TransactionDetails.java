package me.q1zz.spaceis.sdk.modal;

import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class TransactionDetails {

    private UUID id;
    private UUID variantId;
    private UUID serverId;
    private String type;
    private int amount;
    private int leftAmount;
    private String status;
    private String providerId;
    private String providerUrl;
    private String additional;
    private String nick;
    private String method;
    private String methodParameter;
    private String blikStatus;
    private String discountCodeId;
    private String discountCode;
    private String variantName;
    private String serverName;
    private String productName;
    private Date createdAt;

}
