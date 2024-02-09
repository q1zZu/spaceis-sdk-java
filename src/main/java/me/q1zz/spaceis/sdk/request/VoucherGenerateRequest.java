package me.q1zz.spaceis.sdk.request;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

/**
 * Represents a request for generating voucher codes.
 */
@Data
@Builder
public class VoucherGenerateRequest {

    /**
     * The quantity of voucher codes to generate (1-10).
     */
    private int quantity;

    /**
     * The UUID of the server product variant (optional).
     * If not provided, a variant will be random.
     */
    private UUID variantId;

}
