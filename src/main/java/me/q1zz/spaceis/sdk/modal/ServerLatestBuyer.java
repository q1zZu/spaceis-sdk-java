package me.q1zz.spaceis.sdk.modal;

import lombok.Data;

import java.util.Date;
import java.util.UUID;

/**
 * Represents the latest buyer on a server.
 */
@Data
public class ServerLatestBuyer {

    /**
     * The ID of the transaction associated with the latest purchase.
     */
    private UUID transactionId;

    /**
     * The nickname of the latest buyer.
     */
    private String nick;

    /**
     * The payment method used by the latest buyer.
     */
    private String method;

    /**
     * The amount spent by the latest buyer.
     */
    private int amount;

    /**
     * The name of the product purchased by the latest buyer.
     */
    private String productName;

    /**
     * The name of the product variant purchased by the latest buyer.
     */
    private String variantName;

    /**
     * The date of the latest purchase.
     */
    private Date date;

}
