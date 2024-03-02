package me.q1zz.spaceis.sdk.modal.server;

import lombok.Data;

/**
 * Represents a goal associated with a server.
 */
@Data
public class ServerGoal {

    /**
     * The ID of the goal.
     */
    private int id;

    /**
     * The name of the goal.
     */
    private String name;

    /**
     * The percentage completion of the goal.
     */
    private int percentage;

    /**
     * The formatted text describing the goal.
     */
    private String formattedText;

}
