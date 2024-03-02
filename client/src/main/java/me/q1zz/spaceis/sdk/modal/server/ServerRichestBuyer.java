package me.q1zz.spaceis.sdk.modal.server;

import lombok.Data;

/**
 * Represents the richest buyer on a server.
 */
@Data
public class ServerRichestBuyer {

    /**
     * The nickname of the richest buyer.
     */
    private String nick;

    /**
     * The total amount of money spent by the richest buyer.
     */
    private double amount;

    /**
     * The total number of purchases made by the richest buyer.
     */
    private int totalBuys;

}
