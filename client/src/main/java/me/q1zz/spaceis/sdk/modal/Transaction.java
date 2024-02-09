package me.q1zz.spaceis.sdk.modal;

import lombok.Data;

import java.util.UUID;

/**
 * Represents details of a transaction generated for payment.
 */
@Data
public class Transaction {

    /**
     * The ID of the transaction provider.
     */
    private String providerId;

    /**
     * The redirect URL for the transaction.
     */
    private String redirectUrl;

    /**
     * The ID of the transaction.
     */
    private UUID transactionId;

    /**
     * The SMS status of the transaction.
     */
    private String smsStatus;

    /**
     * The type of the transaction.
     */
    private String type;

}
