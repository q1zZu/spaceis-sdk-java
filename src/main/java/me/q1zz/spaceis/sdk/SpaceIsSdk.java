package me.q1zz.spaceis.sdk;

import me.q1zz.spaceis.sdk.modal.*;
import me.q1zz.spaceis.sdk.request.TransactionRequest;
import me.q1zz.spaceis.sdk.request.VoucherGenerateRequest;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.UUID;

public interface SpaceIsSdk {

    @NotNull
    SpaceIsResponse<License> getLicense();

    @NotNull
    SpaceIsResponse<DiscountCode> getDiscountCode(@NotNull String code);

    @NotNull
    SpaceIsResponse<Subpage> getSubpage(@NotNull String slug);

    @NotNull
    SpaceIsResponse<DailyReward> getDailyReward();

    @NotNull
    SpaceIsResponse<Void> redeemDailyReward(@NotNull String nick, @NotNull String recaptchaToken);

    @NotNull
    SpaceIsResponse<TransactionDetails> getTransactionDetails(@NotNull UUID transactionId);

    @NotNull
    SpaceIsResponse<Transaction> initTransaction(@NotNull TransactionRequest transactionRequest);

    @NotNull
    SpaceIsResponse<Void> approveTransaction(@NotNull UUID transactionId);

    @NotNull
    SpaceIsResponse<VoucherState> redeemVoucher(@NotNull String nick, @NotNull String code);

    @NotNull
    SpaceIsResponse<VoucherList> generateVoucher(@NotNull VoucherGenerateRequest voucherGenerateRequest);

    @NotNull
    SpaceIsResponse<ServerCommand[]> getServerCommands(@NotNull UUID serverId, @NotNull String serverToken);

    @NotNull
    SpaceIsResponse<Void> restoreServerCommand(@NotNull UUID serverId, @NotNull String serverToken, @NotNull UUID commandId);

    @NotNull
    SpaceIsResponse<Server[]> getServers();

    @NotNull
    SpaceIsResponse<ServerDetails> getServerDetails(@NotNull UUID serverId);

    @NotNull
    SpaceIsResponse<ServerGoal[]> getServerGoals(@NotNull String param);

}


