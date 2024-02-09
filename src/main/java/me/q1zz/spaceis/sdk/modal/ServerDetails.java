package me.q1zz.spaceis.sdk.modal;

import lombok.Data;

/**
 * Represents details of a server, including its categories and associated license ID.
 */
@Data
public class ServerDetails {

    /**
     * The server details.
     */
    private Server server;

    /**
     * The product categories associated with the server.
     */
    private ServerCategory[] categories;

    /**
     * The ID of the license associated with the server.
     */
    private String licenseId;

}
