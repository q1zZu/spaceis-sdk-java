package me.q1zz.spaceis.sdk.modal.transaction;

import lombok.Data;

import java.util.Date;
import java.util.UUID;

/**
 * Represents details of a transaction.
 */
@Data
public class TransactionDetails {

    /**
     * The ID of the transaction.
     */
    private UUID id;

    /**
     * The ID of the product variant.
     */
    private UUID variantId;

    /**
     * The ID of the server associated with the product variant.
     */
    private UUID serverId;

    /**
     * The type of the transaction.
     */
    private String type;

    /**
     * The total amount of the transaction.
     */
    private int amount;

    /**
     * The remaining amount of the transaction.
     */
    private int leftAmount;

    /**
     * The status of the transaction.
     */
    private String status;

    /**
     * The ID of the transaction provider.
     */
    private String providerId;

    /**
     * The URL provided by the transaction provider.
     */
    private String providerUrl;

    /**
     * Additional information related to the transaction.
     */
    private String additional;

    /**
     * The player nickname associated with the transaction.
     */
    private String nick;

    /**
     * The payment method used for the transaction.
     */
    private String method;

    /**
     * The parameter associated with the payment method (e.g. email address).
     */
    private String methodParameter;

    /**
     * The status of the BLIK payment method, if applicable.
     */
    private String blikStatus;

    /**
     * The ID of the discount code used for the transaction.
     */
    private String discountCodeId;

    /**
     * The discount code used for the transaction.
     */
    private String discountCode;

    /**
     * The name of the product variant.
     */
    private String variantName;

    /**
     * The name of the server.
     */
    private String serverName;

    /**
     * The name of the product.
     */
    private String productName;

    /**
     * The date and time when the transaction was created.
     */
    private Date createdAt;

}
