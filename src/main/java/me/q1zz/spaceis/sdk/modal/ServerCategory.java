package me.q1zz.spaceis.sdk.modal;

import lombok.Data;

import java.util.UUID;

@Data
public class ServerCategory {

    private UUID id;
    private String name;
    private ServerProduct[] products;

}
