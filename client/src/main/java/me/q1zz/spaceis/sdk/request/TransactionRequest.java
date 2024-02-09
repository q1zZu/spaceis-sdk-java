package me.q1zz.spaceis.sdk.request;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

/**
 * Represents a request for a transaction.
 */
@Data
@Builder
public class TransactionRequest {

    /**
     * The UUID of the variant associated with the transaction request.
     */
    private UUID variantId;

    /**
     * The nickname of the player.
     */
    private String nick;

    /**
     * The payment method used for the transaction.
     */
    private String method;

    /**
     * The parameter of the payment method (e.g., email address for regular methods, BLIK code for BLIK, SMS code for SMS).
     */
    private String methodParameter;

    /**
     * Additional parameter for own use (optional).
     */
    private String additional;

    /**
     * The client's IP address.
     */
    private String clientIp;

    /**
     * The ID of the discount code used for the transaction (optional).
     */
    private String discountCodeId;

}
