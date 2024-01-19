package me.q1zz.spaceis.sdk.modal;

import lombok.Data;

@Data
public class ServerDetails {

    private Server server;
    private ServerCategory[] categories;
    private String licenseId;

}
