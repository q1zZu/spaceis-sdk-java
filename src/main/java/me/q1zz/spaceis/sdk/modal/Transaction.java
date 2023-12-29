package me.q1zz.spaceis.sdk.modal;

import lombok.Data;

import java.util.UUID;

@Data
public class Transaction {

    private String providerId;
    private String redirectUrl;
    private UUID transactionId;
    private String smsStatus;
    private String type;

}
