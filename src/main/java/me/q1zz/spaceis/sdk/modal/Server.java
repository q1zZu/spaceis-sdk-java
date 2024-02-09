package me.q1zz.spaceis.sdk.modal;

import lombok.Data;

import java.util.UUID;

/**
 * Represents information about a server.
 * Primarily used as a representation in lists rather than direct server information.
 */
@Data
public class Server {

    /**
     * The ID of the server.
     */
    private UUID id;

    /**
     * The name of the server.
     */
    private String name;

    /**
     * The slug of the server.
     */
    private String slug;

    /**
     * The URL to the image associated with the server.
     */
    private String imageUrl;

    /**
     * Additional information about the server.
     */
    private String additional;

}
