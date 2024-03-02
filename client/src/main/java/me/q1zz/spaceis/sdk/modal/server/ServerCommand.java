package me.q1zz.spaceis.sdk.modal.server;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.q1zz.spaceis.sdk.util.Base64Util;
import org.jetbrains.annotations.NotNull;

/**
 * Represents a server command.
 */
@Data
public class ServerCommand {

    /**
     * The ID of the command.
     */
    private String id;

    /**
     * The Base64-encoded command string.
     */
    private String command;

    /**
     * The nickname associated with the command.
     */
    private String nick;

    /**
     * Indicates whether the command requires the player to be online.
     */
    @SerializedName("requires_player_online")
    private Boolean requirePlayerOnline;

    /**
     * Decodes and retrieves the command string.
     *
     * @return the decoded command string
     */
    @NotNull
    public String getCommand() {
        return Base64Util.decode(this.command);
    }
}
