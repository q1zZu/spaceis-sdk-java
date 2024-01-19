package me.q1zz.spaceis.sdk.modal;

import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class ServerLatestBuyer {

    private UUID transactionId;
    private String nick;
    private String method;
    private int amount;
    private String productName;
    private String variantName;
    private Date date;

}
