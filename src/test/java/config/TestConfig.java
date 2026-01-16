package config;

import io.gatling.javaapi.http.HttpProtocolBuilder;

import static io.gatling.javaapi.http.HttpDsl.http;

public class TestConfig {
    public static final String BASE_URL = "https://dummyjson.com";
    public static final int USERS = 10;
    public static final int RAMP_UP = 60;
    public static final int DURATION = 200;

    // NFRs
    public static final int MAX_RESPONSE_TIME_P95 = 800;
    public static final double MAX_ERROR_PERCENTAGE = 5.0;

    public static HttpProtocolBuilder httpProtocol = http
            .baseUrl(BASE_URL)
            .acceptHeader("application/json")
            .contentTypeHeader("application/json");
}
