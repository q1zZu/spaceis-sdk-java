package me.q1zz.spaceis.sdk;

import me.q1zz.spaceis.sdk.modal.*;
import me.q1zz.spaceis.sdk.modal.server.*;
import me.q1zz.spaceis.sdk.modal.voucher.VoucherList;
import me.q1zz.spaceis.sdk.modal.voucher.VoucherState;
import me.q1zz.spaceis.sdk.request.TransactionRequest;
import me.q1zz.spaceis.sdk.request.VoucherGenerateRequest;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

/**
 * This interface provides methods for interacting with the SpaceIs API.
 * Documentation for the API can be found at: https://api.spaceis.pl/
 */
public interface SpaceIsSdk {

    /**
     * Retrieves the user's license information from the SpaceIs API.
     *
     * @return a response object containing details about the user's license
     */
    @NotNull
    SpaceIsResponse<License> getLicense();

    /**
     * Retrieves information about a discount code from the SpaceIs API based on the provided code.
     *
     * @param code the discount code to retrieve information for
     * @return a response object containing details about the discount code
     */
    @NotNull
    SpaceIsResponse<DiscountCode> getDiscountCode(@NotNull String code);

    /**
     * Retrieves information about a subpage from the SpaceIs API based on the provided slug.
     *
     * @param slug the slug of the subpage to retrieve information for
     * @return a response object containing details about the subpage
     */
    @NotNull
    SpaceIsResponse<Subpage> getSubpage(@NotNull String slug);

    /**
     * Retrieves information about the daily reward from the SpaceIs API.
     *
     * @return a response object containing details about the daily reward
     */
    @NotNull
    SpaceIsResponse<DailyReward> getDailyReward();

    /**
     * Redeems the daily reward for a player with the specified nickname and reCAPTCHA token.
     *
     * @param nick the nickname of the player to redeem the daily reward for
     * @param recaptchaToken the reCAPTCHA token for verification
     */
    @NotNull
    SpaceIsResponse<Void> redeemDailyReward(@NotNull String nick, @NotNull String recaptchaToken);

    /**
     * Retrieves details about a transaction with the specified transaction ID from the SpaceIs API.
     *
     * @param transactionId the UUID of the transaction to retrieve details for
     * @return a response object containing details about the transaction
     */
    @NotNull
    SpaceIsResponse<TransactionDetails> getTransactionDetails(@NotNull UUID transactionId);

    /**
     * Initializes a transaction based on the provided transaction request.
     *
     * @param transactionRequest the request object containing details of the transaction to initialize
     * @return a response object containing details about the initialized transaction
     */
    @NotNull
    SpaceIsResponse<Transaction> initTransaction(@NotNull TransactionRequest transactionRequest);

    /**
     * Approves a transaction with the specified transaction ID.
     *
     * @param transactionId the UUID of the transaction to approve
     */
    @NotNull
    SpaceIsResponse<Void> approveTransaction(@NotNull UUID transactionId);

    /**
     * Redeems a voucher with the specified code for the player with the given nickname.
     *
     * @param nick the nickname of the player redeeming the voucher
     * @param code the code of the voucher to redeem
     * @return a response object containing the state of the voucher redemption process
     */
    @NotNull
    SpaceIsResponse<VoucherState> redeemVoucher(@NotNull String nick, @NotNull String code);

    /**
     * Generates vouchers based on the provided voucher generation request.
     *
     * @param voucherGenerateRequest the request object containing details for voucher generation
     * @return a response object containing the list of generated vouchers
     */
    @NotNull
    SpaceIsResponse<VoucherList> generateVoucher(@NotNull VoucherGenerateRequest voucherGenerateRequest);

    /**
     * Retrieves server commands for the server with the specified ID and token.
     *
     * @param serverId the UUID of the server to retrieve commands for
     * @param serverToken the token of the server for authentication
     * @return a response object containing an array of server commands
     */
    @NotNull
    SpaceIsResponse<ServerCommand[]> getServerCommands(@NotNull UUID serverId, @NotNull String serverToken);

    /**
     * Restores a server command with the specified command ID for the server identified by the server ID and token.
     *
     * @param serverId the UUID of the server to restore the command for
     * @param serverToken the token of the server for authentication
     * @param commandId the UUID of the command to restore
     */
    @NotNull
    SpaceIsResponse<Void> restoreServerCommand(@NotNull UUID serverId, @NotNull String serverToken, @NotNull UUID commandId);

    /**
     * Retrieves servers from SpaceIs API.
     *
     * @return a response object containing an array of servers
     */
    @NotNull
    SpaceIsResponse<Server[]> getServers();

    /**
     * Retrieves details about a server with the specified ID.
     *
     * @param serverId the UUID of the server to retrieve details for
     * @return a response object containing details about the server
     */
    @NotNull
    SpaceIsResponse<ServerDetails> getServerDetails(@NotNull UUID serverId);

    /**
     * Retrieves goals for a server based on the provided server ID or slug.
     *
     * @param param the ID or slug of the server to retrieve goals for
     * @return a response object containing an array of server goals
     */
    @NotNull
    SpaceIsResponse<ServerGoal[]> getServerGoals(@NotNull String param);

    /**
     * Retrieves the latest buyers for a server based on the provided server ID or slug.
     *
     * @param param the ID or slug of the server to retrieve the latest buyers for
     * @param limit the maximum number of the latest buyers to retrieve (1-30)
     * @return a response object containing an array of the latest buyers for the server
     */
    @NotNull
    SpaceIsResponse<ServerLatestBuyer[]> getServerLatestBuyers(@NotNull String param, int limit);

    /**
     * Retrieves the richest buyers for a server based on the provided server ID or slug.
     *
     * @param param the ID or slug of the server to retrieve the richest buyers for
     * @param limit the maximum number of the richest buyers to retrieve (1-30)
     * @return a response object containing an array of the richest buyers for the server
     */
    @NotNull
    SpaceIsResponse<ServerRichestBuyer[]> getServerRichestBuyers(@NotNull String param, int limit);

    /**
     * Retrieves details of a product for a server based on the provided server ID or slug,
     * category ID, and product ID.
     *
     * @param param the ID or slug of the server to retrieve product details for
     * @param categoryId the UUID of the category containing the product
     * @param productId the UUID of the product to retrieve details for
     * @return a response object containing details of the product and its variants for the server
     */
    @NotNull
    SpaceIsResponse<ServerProductDetails> getServerProductDetails(@NotNull String param, @NotNull UUID categoryId, @NotNull UUID productId);

}


