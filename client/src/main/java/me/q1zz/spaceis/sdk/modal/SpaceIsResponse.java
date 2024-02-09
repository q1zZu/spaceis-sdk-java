package me.q1zz.spaceis.sdk.modal;

import lombok.Data;

/**
 * Represents a response from the SpaceIs API.
 *
 * @param <T> the type of data contained in the response
 */
@Data
public class SpaceIsResponse<T> {

    /**
     * Indicates whether the request was successful.
     */
    private boolean success;

    /**
     * The type of the response.
     */
    private String type;

    /**
     * The data contained in the response.
     */
    private T data;

}
