package me.q1zz.spaceis.sdk.modal;

import lombok.Data;

import java.util.UUID;

@Data
public class Server {

    private UUID id;
    private String name;
    private String slug;
    private String imageUrl;
    private String additional;

}
