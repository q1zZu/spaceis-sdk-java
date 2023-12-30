package me.q1zz.spaceis.sdk.modal;

import lombok.Data;

import java.util.UUID;

@Data
public class VoucherState {

    private String state;
    private UUID transactionId;

}
