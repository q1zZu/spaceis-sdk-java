package me.q1zz.spaceis.sdk;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import kong.unirest.*;
import me.q1zz.spaceis.sdk.adapter.BooleanAdapter;
import me.q1zz.spaceis.sdk.exception.*;
import me.q1zz.spaceis.sdk.modal.*;
import me.q1zz.spaceis.sdk.request.TransactionRequest;
import me.q1zz.spaceis.sdk.request.VoucherGenerateRequest;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class SpaceIsSdkClient implements SpaceIsSdk {

    private final static String BASE_URL = "https://api.spaceis.pl/v4";
    private final static String USER_AGENT = "spaceis-sdk-java/q1zZ";
    private final UnirestInstance unirest;
    private final Gson gson;

    public SpaceIsSdkClient(@NotNull String apiKey) {
        this(BASE_URL, apiKey);
    }

    public SpaceIsSdkClient(@NotNull String baseUrl, @NotNull String apiKey) {
        this(new Config()
                .defaultBaseUrl(baseUrl)
                .addDefaultHeader("Authorization", "Bearer " + apiKey)
        );
    }

    public SpaceIsSdkClient(@NotNull Config config) {
        this.unirest = new UnirestInstance(config
                .addDefaultHeader("User-Agent", USER_AGENT)
                .addDefaultHeader("Content-Type", "application/json")
                .addDefaultHeader("Accept", "application/json")
                .connectTimeout(5000)
                .enableCookieManagement(false)
        );
        this.gson = new GsonBuilder()
                .registerTypeAdapter(Boolean.class, new BooleanAdapter())
                .create();
    }

    private <T> T sendRequest(@NotNull String path, @NotNull HttpMethod method, @NotNull Type responseType, @Nullable Object body, @NotNull Map<String, String> headers, @NotNull Map<Integer, SpaceIsSdkException> responseActions) throws SpaceIsSdkException {

        final HttpResponse<JsonNode> response = switch (method.name()) {
            case "GET" -> this.unirest.get(path)
                    .headers(headers)
                    .asJson();
            case "POST", "PUT", "DELETE" -> this.unirest.request(method.name(), path)
                    .body(this.gson.toJson(body))
                    .headers(headers)
                    .asJson();
            default -> throw new SpaceIsSdkException("Unsupported request method: " + method.name());
        };

        final int responseStatus = response.getStatus();

        if(responseStatus == 429) {
            throw new RateLimitException("rate-limit reached!");
        }

        if(!response.isSuccess()) {
            throw responseActions.getOrDefault(responseStatus, new SpaceIsSdkException(response));
        }

        final JsonNode responseBody = response.getBody();
        final Object responseData = responseBody.isArray() ?
                responseBody.getArray() :
                responseBody.getObject();

        return this.gson.fromJson(responseData.toString(), responseType);
    }

    private <T> SpaceIsResponse<T> sendRequest(@NotNull String path, @NotNull HttpMethod method, @NotNull Class<T> responseType, @Nullable Object body, @NotNull Map<String, String> headers, @NotNull Map<Integer, SpaceIsSdkException> responseActions) throws SpaceIsSdkException {

        final TypeToken<?> spaceIsResponse = TypeToken.getParameterized(SpaceIsResponse.class, responseType);
        final Type spaceIsResponseType = spaceIsResponse.getType();

        return this.sendRequest(path, method, spaceIsResponseType, body, headers, responseActions);
    }

    private <T> SpaceIsResponse<T> sendRequest(@NotNull String path, @NotNull HttpMethod method, @NotNull Class<T> responseType, @Nullable Object body, @NotNull Map<Integer, SpaceIsSdkException> responseActions) throws SpaceIsSdkException {
        return this.sendRequest(path, method, responseType, body, new HashMap<>(), responseActions);
    }

    private <T> SpaceIsResponse<T> sendRequest(@NotNull String path, @NotNull HttpMethod method, @NotNull Class<T> responseType, @Nullable Object body) throws SpaceIsSdkException {
        return this.sendRequest(path, method, responseType, body, new HashMap<>());
    }


    @Override
    @NotNull
    public SpaceIsResponse<License> getLicense() {
        return this.sendRequest("/license", HttpMethod.GET, License.class, null);
    }

    @Override
    @NotNull
    public SpaceIsResponse<DiscountCode> getDiscountCode(@NotNull String code) throws NotFoundException {
        return this.sendRequest("/discount_code/" + code, HttpMethod.GET, DiscountCode.class, null, Map.of(
                404, new NotFoundException("discount code not found!")
        ));
    }

    @Override
    @NotNull
    public SpaceIsResponse<Subpage> getSubpage(@NotNull String slug) throws NotFoundException {
        return this.sendRequest("/subpage/" + slug, HttpMethod.GET, Subpage.class, null, Map.of(
                404, new NotFoundException("subpage not found!")
        ));
    }

    @Override
    @NotNull
    public SpaceIsResponse<DailyReward> getDailyReward() throws DailyRewardDisabledException {
        return this.sendRequest("/daily_reward", HttpMethod.GET, DailyReward.class, null, Map.of(
                404, new DailyRewardDisabledException("Daily reward is disabled!")
        ));
    }

    @Override
    @NotNull
    public SpaceIsResponse<Void> redeemDailyReward(@NotNull String nick, @NotNull String recaptchaToken) throws DailyRewardDisabledException, DailyRewardAlreadyReceivedException, DailyRewardWrongRecaptchaTokenException {
        return this.sendRequest("/daily_reward", HttpMethod.POST, Void.class,
                Map.of(
                        "nick", nick,
                        "recaptchaToken", recaptchaToken),
                Map.of(
                        400, new DailyRewardWrongRecaptchaTokenException("Invalid recaptcha token!"),
                        404, new DailyRewardDisabledException("Daily reward is disabled!"),
                        403, new DailyRewardAlreadyReceivedException("Daily reward for this nickname is already received!")));
    }

    @Override
    @NotNull
    public SpaceIsResponse<TransactionDetails> getTransactionDetails(@NotNull UUID transactionId) throws NotFoundException {
        return this.sendRequest("/transaction/info/" + transactionId, HttpMethod.GET, TransactionDetails.class,null, Map.of(
                404, new NotFoundException("Transaction not found!")
        ));
    }

    @Override
    @NotNull
    public SpaceIsResponse<Transaction> initTransaction(@NotNull TransactionRequest transactionRequest) throws TransactionInitException {
        return this.sendRequest("/transaction/variantPayment", HttpMethod.POST, Transaction.class, transactionRequest, Map.of(
                403, new TransactionInitException("Sms code already used!"),
                404, new TransactionInitException("Invalid sms code!")
        ));
    }

    @Override
    @NotNull
    public SpaceIsResponse<Void> approveTransaction(@NotNull UUID transactionId) throws TransactionApproveException {
        return this.sendRequest(String.format("/transaction/%s/approve", transactionId), HttpMethod.POST, Void.class, null, Map.of(
            400, new TransactionApproveException("Transaction already approved!")
        ));
    }

    @Override
    @NotNull
    public SpaceIsResponse<VoucherState> redeemVoucher(@NotNull String nick, @NotNull String code) throws VoucherAlreadyUsedException, VoucherNotFoundException {
        return this.sendRequest("/voucher", HttpMethod.POST, VoucherState.class,
                Map.of(
                        "nick", nick,
                        "code", code),
                Map.of(
                        403, new VoucherAlreadyUsedException("Voucher already used!"),
                        404, new VoucherNotFoundException("Voucher not found!")
                ));
    }

    @Override
    @NotNull
    public SpaceIsResponse<VoucherList> generateVoucher(@NotNull VoucherGenerateRequest voucherGenerateRequest) {
        return this.sendRequest("/voucher/generate", HttpMethod.POST, VoucherList.class, voucherGenerateRequest);
    }

    @Override
    @NotNull
    public SpaceIsResponse<ServerCommand[]> getServerCommands(@NotNull UUID serverId, @NotNull String serverToken) {
        return this.sendRequest(String.format("/server/%s/commands", serverId), HttpMethod.GET, ServerCommand[].class, null,
                Map.of(
                        "X-SPACEIS-SERVER-TOKEN", serverToken),
                Map.of()
        );
    }

    @Override
    @NotNull
    public SpaceIsResponse<Void> restoreServerCommand(@NotNull UUID serverId, @NotNull String serverToken, @NotNull UUID commandId) throws ServerCommandNotFoundException {
        return this.sendRequest(String.format("/server/%s/commands/%s/restore", serverId, commandId), HttpMethod.POST, Void.class, null,
                Map.of(
                        "X-SPACEIS-SERVER-TOKEN", serverToken),
                Map.of(
                        404, new ServerCommandNotFoundException("Command not found!")
        ));
    }

    @Override
    @NotNull
    public SpaceIsResponse<Server[]> getServers() {
        return this.sendRequest("/servers", HttpMethod.GET, Server[].class, null);
    }

    @Override
    @NotNull
    public SpaceIsResponse<ServerDetails> getServerDetails(@NotNull UUID serverId) throws ServerNotFoundException {
        return this.sendRequest("/server/" + serverId, HttpMethod.GET, ServerDetails.class, null, Map.of(
                404, new ServerNotFoundException("Server not found!")
        ));
    }

    @Override
    @NotNull
    public SpaceIsResponse<ServerGoal[]> getServerGoals(@NotNull String param) throws ServerNotFoundException {
        return this.sendRequest(String.format("/server/%s/goals", param), HttpMethod.GET, ServerGoal[].class, null, Map.of(
                404, new ServerNotFoundException("Server not found!")
        ));
    }

    @Override
    @NotNull
    public SpaceIsResponse<ServerLatestBuyer[]> getServerLatestBuyers(@NotNull String param, int limit) {
        return this.sendRequest(String.format("/server/%s/latest_buyers?limit=%s", param, limit), HttpMethod.GET, ServerLatestBuyer[].class, null, Map.of(
                404, new ServerNotFoundException("Server not found!")
        ));
    }


}
