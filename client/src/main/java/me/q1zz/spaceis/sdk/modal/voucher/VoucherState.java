package me.q1zz.spaceis.sdk.modal.voucher;

import lombok.Data;

import java.util.UUID;

/**
 * Represents the state of a voucher.
 */
@Data
public class VoucherState {

    /**
     * The state of the voucher.
     */
    private String state;

    /**
     * The ID of the transaction associated with the voucher.
     */
    private UUID transactionId;

}
