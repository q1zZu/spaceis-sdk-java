package me.q1zz.spaceis.sdk.modal;

import lombok.Data;

@Data
public class SpaceIsResponse<T> {

    private boolean success;
    private String type;
    private T data;

}
