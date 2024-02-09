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

/**
 * Implementation of the SpaceIsSdk interface for interacting with the SpaceIs API.
 */
public class SpaceIsSdkClient implements SpaceIsSdk {

    /**
     * The base URL for the SpaceIs API.
     */
    private final static String BASE_URL = "https://api.spaceis.pl/v4";

    /**
     * The user agent identifying the SpaceIs SDK Java client.
     */
    private final static String USER_AGENT = "spaceis-sdk-java/q1zZ";

    /**
     * The instance of Unirest used for making HTTP requests.
     */
    private final UnirestInstance unirest;

    /**
     * The Gson object used for JSON serialization and deserialization.
     */
    private final Gson gson;

    /**
     * Constructs a SpaceIsSdkClient instance with the default base URL and provided API key.
     *
     * @param apiKey the API key used for authentication
     */
    public SpaceIsSdkClient(@NotNull String apiKey) {
        this(BASE_URL, apiKey);
    }

    /**
     * Constructs a SpaceIsSdkClient instance with the provided base URL and API key.
     *
     * @param baseUrl the base URL of the SpaceIs API
     * @param apiKey the API key used for authentication
     */
    public SpaceIsSdkClient(@NotNull String baseUrl, @NotNull String apiKey) {
        this(new Config()
                .defaultBaseUrl(baseUrl)
                .addDefaultHeader("Authorization", "Bearer " + apiKey)
        );
    }

    /**
     * Constructs a SpaceIsSdkClient instance with the provided configuration for Unirest.
     *
     * @param config the configuration for the Unirest instance used by the SpaceIsSdkClient
     */
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

    /**
     * Sends an HTTP request to the SpaceIs API.
     *
     * @param path the path of the API endpoint
     * @param method the HTTP method of the request
     * @param responseType the type of the response object
     * @param body the request body object
     * @param headers the headers to be included in the request
     * @param responseActions the map of response actions to be taken based on HTTP status codes
     * @param <T> the type of the response object
     * @return the response object of the specified type
     * @throws SpaceIsSdkException if an error occurs while sending the request or processing the response,
     *         only if no response actions were provided for specified error code
     * @throws RateLimitException if the rate limit for the API has been reached
     */
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

    /**
     * Sends an HTTP request to the SpaceIs API and returns a SpaceIsResponse object.
     *
     * @param path the path of the API endpoint
     * @param method the HTTP method of the request
     * @param responseType the class of the response object
     * @param body the request body object
     * @param headers the headers to be included in the request
     * @param responseActions the map of response actions to be taken based on HTTP status codes
     * @param <T> the type of the response object
     * @return a SpaceIsResponse object containing the response data
     * @throws SpaceIsSdkException if an error occurs while sending the request or processing the response,
     *         only if no response actions were provided for specified error code
     * @throws RateLimitException if the rate limit for the API has been reached
     */
    private <T> SpaceIsResponse<T> sendRequest(@NotNull String path, @NotNull HttpMethod method, @NotNull Class<T> responseType, @Nullable Object body, @NotNull Map<String, String> headers, @NotNull Map<Integer, SpaceIsSdkException> responseActions) throws SpaceIsSdkException {

        final TypeToken<?> spaceIsResponse = TypeToken.getParameterized(SpaceIsResponse.class, responseType);
        final Type spaceIsResponseType = spaceIsResponse.getType();

        return this.sendRequest(path, method, spaceIsResponseType, body, headers, responseActions);
    }

    /**
     * Sends an HTTP request to the SpaceIs API and returns a SpaceIsResponse object.
     *
     * @param path the path of the API endpoint
     * @param method the HTTP method of the request
     * @param responseType the class of the response object
     * @param body the request body object
     * @param responseActions the map of response actions to be taken based on HTTP status codes
     * @param <T> the type of the response object
     * @return a SpaceIsResponse object containing the response data
     * @throws SpaceIsSdkException if an error occurs while sending the request or processing the response,
     *         only if no response actions were provided for specified error code
     * @throws RateLimitException if the rate limit for the API has been reached
     */
    private <T> SpaceIsResponse<T> sendRequest(@NotNull String path, @NotNull HttpMethod method, @NotNull Class<T> responseType, @Nullable Object body, @NotNull Map<Integer, SpaceIsSdkException> responseActions) throws SpaceIsSdkException {
        return this.sendRequest(path, method, responseType, body, new HashMap<>(), responseActions);
    }

    /**
     * Sends an HTTP request to the SpaceIs API and returns a SpaceIsResponse object.
     *
     * @param path the path of the API endpoint
     * @param method the HTTP method of the request
     * @param responseType the class of the response object
     * @param body the request body object
     * @param <T> the type of the response object
     * @return a SpaceIsResponse object containing the response data
     * @throws SpaceIsSdkException if an error occurs while sending the request or processing the response
     */
    private <T> SpaceIsResponse<T> sendRequest(@NotNull String path, @NotNull HttpMethod method, @NotNull Class<T> responseType, @Nullable Object body) throws SpaceIsSdkException {
        return this.sendRequest(path, method, responseType, body, new HashMap<>());
    }


    /**
     * Retrieves the user's license information from the SpaceIs API.
     *
     * @return a SpaceIsResponse object containing details about the user's license
     * @throws SpaceIsSdkException if an error occurs while sending the request or processing the response
     */
    @Override
    @NotNull
    public SpaceIsResponse<License> getLicense() {
        return this.sendRequest("/license", HttpMethod.GET, License.class, null);
    }

    /**
     * Retrieves details about a discount code from the SpaceIs API based on the provided code.
     *
     * @param code the discount code to retrieve details for
     * @return a SpaceIsResponse object containing details about the discount code
     * @throws SpaceIsSdkException if an error occurs while sending the request or processing the response
     * @throws NotFoundException if the specified discount code is not found
     */
    @Override
    @NotNull
    public SpaceIsResponse<DiscountCode> getDiscountCode(@NotNull String code) throws NotFoundException {
        return this.sendRequest("/discount_code/" + code, HttpMethod.GET, DiscountCode.class, null, Map.of(
                404, new NotFoundException("discount code not found!")
        ));
    }

    /**
     * Retrieves details about a subpage from the SpaceIs API based on the provided slug.
     *
     * @param slug the slug of the subpage to retrieve details for
     * @return a SpaceIsResponse object containing details about the subpage
     * @throws SpaceIsSdkException if an error occurs while sending the request or processing the response
     * @throws NotFoundException if the specified subpage is not found
     */
    @Override
    @NotNull
    public SpaceIsResponse<Subpage> getSubpage(@NotNull String slug) throws NotFoundException {
        return this.sendRequest("/subpage/" + slug, HttpMethod.GET, Subpage.class, null, Map.of(
                404, new NotFoundException("subpage not found!")
        ));
    }

    /**
     * Retrieves details about the daily reward from the SpaceIs API.
     *
     * @return a SpaceIsResponse object containing details about the daily reward
     * @throws SpaceIsSdkException if an error occurs while sending the request or processing the response
     * @throws DailyRewardDisabledException if the daily reward feature is disabled
     */
    @Override
    @NotNull
    public SpaceIsResponse<DailyReward> getDailyReward() throws DailyRewardDisabledException {
        return this.sendRequest("/daily_reward", HttpMethod.GET, DailyReward.class, null, Map.of(
                404, new DailyRewardDisabledException("Daily reward is disabled!")
        ));
    }

    /**
     * Redeems the daily reward for the specified player with the provided nickname and reCaptcha token.
     *
     * @param nick the nickname of the player to redeem the daily reward for
     * @param recaptchaToken the reCaptcha token for verification
     * @return a SpaceIsResponse object indicating the success or failure of the redemption process
     * @throws SpaceIsSdkException if an error occurs while sending the request or processing the response
     * @throws DailyRewardDisabledException if the daily reward feature is disabled
     * @throws DailyRewardAlreadyReceivedException if the daily reward for the specified nickname has already been received
     * @throws DailyRewardWrongRecaptchaTokenException if the provided reCaptcha token is invalid
     */
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

    /**
     * Retrieves details about a transaction from the SpaceIs API based on the provided transaction ID.
     *
     * @param transactionId the UUID of the transaction to retrieve details for
     * @return a SpaceIsResponse object containing details about the transaction
     * @throws SpaceIsSdkException if an error occurs while sending the request or processing the response
     * @throws NotFoundException if the specified transaction is not found
     */
    @Override
    @NotNull
    public SpaceIsResponse<TransactionDetails> getTransactionDetails(@NotNull UUID transactionId) throws NotFoundException {
        return this.sendRequest("/transaction/info/" + transactionId, HttpMethod.GET, TransactionDetails.class,null, Map.of(
                404, new NotFoundException("Transaction not found!")
        ));
    }

    /**
     * Initiates a transaction using the provided transaction request.
     *
     * @param transactionRequest the transaction request containing details of the payment
     * @return a SpaceIsResponse object containing details about the initiated transaction
     * @throws SpaceIsSdkException if an error occurs while sending the request or processing the response
     * @throws TransactionInitException if the transaction initiation fails due to an invalid or already used SMS code
     */
    @Override
    @NotNull
    public SpaceIsResponse<Transaction> initTransaction(@NotNull TransactionRequest transactionRequest) throws TransactionInitException {
        return this.sendRequest("/transaction/variantPayment", HttpMethod.POST, Transaction.class, transactionRequest, Map.of(
                403, new TransactionInitException("Sms code already used!"),
                404, new TransactionInitException("Invalid sms code!")
        ));
    }

    /**
     * Approves a transaction with the specified transaction ID.
     *
     * @param transactionId the UUID of the transaction to approve
     * @throws SpaceIsSdkException if an error occurs while sending the request or processing the response
     * @throws TransactionApproveException if the transaction approval fails, such as if the transaction is already approved
     */
    @Override
    @NotNull
    public SpaceIsResponse<Void> approveTransaction(@NotNull UUID transactionId) throws TransactionApproveException {
        return this.sendRequest(String.format("/transaction/%s/approve", transactionId), HttpMethod.POST, Void.class, null, Map.of(
            400, new TransactionApproveException("Transaction already approved!")
        ));
    }

    /**
     * Redeems a voucher with the specified code for the provided player nickname.
     *
     * @param nick the nickname of the player redeeming the voucher
     * @param code the code of the voucher to redeem
     * @return a SpaceIsResponse object containing the state of the redeemed voucher
     * @throws SpaceIsSdkException if an error occurs while sending the request or processing the response
     * @throws VoucherAlreadyUsedException if the voucher has already been used
     * @throws VoucherNotFoundException if the specified voucher code is not found
     */
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

    /**
     * Generates vouchers based on the provided voucher generation request.
     *
     * @param voucherGenerateRequest the request containing details for generating vouchers
     * @return a SpaceIsResponse object containing the list of generated vouchers
     * @throws SpaceIsSdkException if an error occurs while sending the request or processing the response
     */
    @Override
    @NotNull
    public SpaceIsResponse<VoucherList> generateVoucher(@NotNull VoucherGenerateRequest voucherGenerateRequest) {
        return this.sendRequest("/voucher/generate", HttpMethod.POST, VoucherList.class, voucherGenerateRequest);
    }

    /**
     * Retrieves the commands available for the specified server.
     *
     * @param serverId the UUID of the server to retrieve commands for
     * @param serverToken the token associated with the server for authentication
     * @return a SpaceIsResponse object containing an array of server commands
     * @throws SpaceIsSdkException if an error occurs while sending the request or processing the response
     */
    @Override
    @NotNull
    public SpaceIsResponse<ServerCommand[]> getServerCommands(@NotNull UUID serverId, @NotNull String serverToken) {
        return this.sendRequest(String.format("/server/%s/commands", serverId), HttpMethod.GET, ServerCommand[].class, null,
                Map.of(
                        "X-SPACEIS-SERVER-TOKEN", serverToken),
                Map.of()
        );
    }

    /**
     * Restores a previously fetched server command with the specified command ID for the specified server.
     *
     * @param serverId the UUID of the server where the command should be restored
     * @param serverToken the token associated with the server for authentication
     * @param commandId the UUID of the command to restore
     * @throws SpaceIsSdkException if an error occurs while sending the request or processing the response
     * @throws ServerCommandNotFoundException if the specified command is not found
     */
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

    /**
     * Retrieves the list of servers from the SpaceIs API.
     *
     * @return a SpaceIsResponse object containing an array of servers
     * @throws SpaceIsSdkException if an error occurs while sending the request or processing the response
     */
    @Override
    @NotNull
    public SpaceIsResponse<Server[]> getServers() {
        return this.sendRequest("/servers", HttpMethod.GET, Server[].class, null);
    }

    /**
     * Retrieves details about the server with the specified server ID.
     *
     * @param serverId the UUID of the server to retrieve details for
     * @return a SpaceIsResponse object containing details about the server
     * @throws SpaceIsSdkException if an error occurs while sending the request or processing the response
     * @throws ServerNotFoundException if the specified server is not found
     */
    @Override
    @NotNull
    public SpaceIsResponse<ServerDetails> getServerDetails(@NotNull UUID serverId) throws ServerNotFoundException {
        return this.sendRequest("/server/" + serverId, HttpMethod.GET, ServerDetails.class, null, Map.of(
                404, new ServerNotFoundException("Server not found!")
        ));
    }

    /**
     * Retrieves goals for a server based on the provided server ID or slug.
     *
     * @param param the ID or slug of the server to retrieve goals for
     * @return a SpaceIsResponse object containing an array of server goals
     * @throws SpaceIsSdkException if an error occurs while sending the request or processing the response
     * @throws ServerNotFoundException if the specified server is not found
     */
    @Override
    @NotNull
    public SpaceIsResponse<ServerGoal[]> getServerGoals(@NotNull String param) throws ServerNotFoundException {
        return this.sendRequest(String.format("/server/%s/goals", param), HttpMethod.GET, ServerGoal[].class, null, Map.of(
                404, new ServerNotFoundException("Server not found!")
        ));
    }

    /**
     * Retrieves the latest buyers for a server based on the provided server ID or slug.
     *
     * @param param the ID or slug of the server to retrieve the latest buyers for
     * @param limit the maximum number of the latest buyers to retrieve (1-30)
     * @return a SpaceIsResponse object containing an array of the latest buyers for the server
     * @throws SpaceIsSdkException if an error occurs while sending the request or processing the response
     * @throws ServerNotFoundException if the specified server is not found
     */
    @Override
    @NotNull
    public SpaceIsResponse<ServerLatestBuyer[]> getServerLatestBuyers(@NotNull String param, int limit) {
        return this.sendRequest(String.format("/server/%s/latest_buyers?limit=%s", param, limit), HttpMethod.GET, ServerLatestBuyer[].class, null, Map.of(
                404, new ServerNotFoundException("Server not found!")
        ));
    }

    /**
     * Retrieves the richest buyers for a server based on the provided server ID or slug.
     *
     * @param param the ID or slug of the server to retrieve the richest buyers for
     * @param limit the maximum number of the richest buyers to retrieve (1-30)
     * @return a SpaceIsResponse object containing an array of the richest buyers for the server
     * @throws SpaceIsSdkException if an error occurs while sending the request or processing the response
     * @throws ServerNotFoundException if the specified server is not found
     */
    @Override
    @NotNull
    public SpaceIsResponse<ServerRichestBuyer[]> getServerRichestBuyers(@NotNull String param, int limit) {
        return this.sendRequest(String.format("/server/%s/richest?limit=%s", param, limit), HttpMethod.GET, ServerRichestBuyer[].class, null, Map.of(
                404, new ServerNotFoundException("Server not found!")
        ));
    }

    /**
     * Retrieves details of a product for a server based on the provided server ID or slug,
     * category ID, and product ID.
     *
     * @param param the ID or slug of the server to retrieve product details for
     * @param categoryId the UUID of the category associated with the product
     * @param productId the UUID of the product to retrieve details for
     * @return a SpaceIsResponse object containing details about the product variants
     * @throws SpaceIsSdkException if an error occurs while sending the request or processing the response
     * @throws NotFoundException if the specified server, category, or product is not found
     */
    @Override
    @NotNull
    public SpaceIsResponse<ServerProductDetails> getServerProductDetails(@NotNull String param, @NotNull UUID categoryId, @NotNull UUID productId) throws NotFoundException {
        return this.sendRequest(String.format("/server/%s/category/%s/product/%s/variants", param, categoryId, productId), HttpMethod.GET, ServerProductDetails.class, null, Map.of(
            404, new NotFoundException("Server, category or product not found!")
        ));
    }

}
