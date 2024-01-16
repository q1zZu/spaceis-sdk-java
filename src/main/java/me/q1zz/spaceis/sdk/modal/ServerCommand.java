package me.q1zz.spaceis.sdk.modal;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.q1zz.spaceis.sdk.util.Base64Util;
import org.jetbrains.annotations.NotNull;

@Data
public class ServerCommand {

    private String id;
    private String command;
    private String nick;

    @SerializedName("requires_player_online")
    private Boolean requirePlayerOnline;

    @NotNull
    public String getCommand() {
        return Base64Util.decode(this.command);
    }
}
