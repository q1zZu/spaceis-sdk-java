package me.q1zz.spaceis.sdk;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import kong.unirest.*;
import me.q1zz.spaceis.sdk.adapter.BooleanAdapter;
import me.q1zz.spaceis.sdk.exception.*;
import me.q1zz.spaceis.sdk.modal.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

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

    private <T> T sendRequest(@NotNull String path, @NotNull HttpMethod method, @Nullable Object body, @NotNull Type responseType, @NotNull Map<Integer, SpaceIsSdkException> responseActions) throws SpaceIsSdkException {

        final HttpResponse<JsonNode> response = switch (method.name()) {
            case "GET" -> this.unirest.get(path)
                    .asJson();
            case "POST", "PUT", "DELETE" -> this.unirest.request(method.name(), path)
                    .body(this.gson.toJson(body))
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

    private <T> T sendRequest(@NotNull String path, @NotNull HttpMethod method, @Nullable Object body, @NotNull Type responseType) throws SpaceIsSdkException {
        return this.sendRequest(path, method, body, responseType, new HashMap<>());
    }

    private <T> SpaceIsResponse<T> sendRequest(@NotNull String path, @NotNull HttpMethod method, @Nullable Object body, @NotNull Class<T> responseType, @NotNull Map<Integer, SpaceIsSdkException> responseActions) throws SpaceIsSdkException {

        final TypeToken<?> spaceIsResponse = TypeToken.getParameterized(SpaceIsResponse.class, responseType);
        final Type spaceIsResponseType = spaceIsResponse.getType();

        return this.sendRequest(path, method, body, spaceIsResponseType, responseActions);
    }

    private <T> SpaceIsResponse<T> sendRequest(@NotNull String path, @NotNull HttpMethod method, @Nullable Object body, @NotNull Class<T> responseType) throws SpaceIsSdkException {

        final TypeToken<?> spaceIsResponse = TypeToken.getParameterized(SpaceIsResponse.class, responseType);
        final Type spaceIsResponseType = spaceIsResponse.getType();

        return this.sendRequest(path, method, body, spaceIsResponseType);
    }


    @Override
    @NotNull
    public SpaceIsResponse<License> getLicense() {
        return this.sendRequest("/license", HttpMethod.GET, null, License.class);
    }

    @Override
    @NotNull
    public SpaceIsResponse<DiscountCode> getDiscountCode(@NotNull String code) throws NotFoundException {
        return this.sendRequest("/discount_code/" + code, HttpMethod.GET, null, DiscountCode.class, Map.of(
                404, new NotFoundException("discount code not found!")
        ));
    }

    @Override
    public @NotNull SpaceIsResponse<Subpage> getSubpage(@NotNull String slug) throws NotFoundException {
        return this.sendRequest("/subpage" + slug, HttpMethod.GET, null, Subpage.class, Map.of(
                404, new NotFoundException("subpage not found!")
        ));
    }

    @Override
    public @NotNull SpaceIsResponse<DailyReward> getDailyReward() throws DailyRewardDisabledException {
        return this.sendRequest("/daily_reward", HttpMethod.GET, null, DailyReward.class, Map.of(
                404, new DailyRewardDisabledException("Daily reward is disabled!")
        ));
    }

    @Override
    public @NotNull SpaceIsResponse<Void> redeemDailyReward(@NotNull String nick, @NotNull String recaptchaToken) throws DailyRewardDisabledException, DailyRewardAlreadyReceivedException, DailyRewardWrongRecaptchaTokenException {
        return this.sendRequest("/daily_reward", HttpMethod.POST, Map.of(
                        "nick", nick,
                        "recaptchaToken", recaptchaToken
                ), Void.class, Map.of(
                        400, new DailyRewardWrongRecaptchaTokenException("Invalid recaptcha token!"),
                        404, new DailyRewardDisabledException("Daily reward is disabled!"),
                        403, new DailyRewardAlreadyReceivedException("Daily reward for this nickname is already received!")));
    }


}
